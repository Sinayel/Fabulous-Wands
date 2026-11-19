package tech.sinayel.fabulouswands.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import tech.sinayel.fabulouswands.block.ModBlocks;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SpellEntity extends ThrownItemEntity implements Ownable {
    @Nullable
    private boolean teleported;

    public SpellEntity(EntityType<? extends SpellEntity> type, World world) {
        super(type, world);
    }

    public SpellEntity(World world, LivingEntity owner) {
        super(ModEntities.SPELL, owner, world);
    }

    public SpellEntity(World world, double x, double y, double z) {
        super(ModEntities.SPELL, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModBlocks.NOBLE_PLANK.asItem(); // apparence en vol (temporaire
    }

    @Override
    protected void onEntityHit(EntityHitResult hit) {
        super.onEntityHit(hit);
        hit.getEntity().damage(this.getDamageSources().thrown(this, this.getOwner()), 13.0F);
    }

    @Override
    protected void onCollision(HitResult hit) {
        super.onCollision(hit);
        if (!this.getWorld().isClient) {
            this.explode();
            this.discard(); // disparaît à l'impact
        }
    }

    // Explosion fonction
    private static final ExplosionBehavior TELEPORTED_EXPLOSION_BEHAVIOR = new ExplosionBehavior() {
        @Override
        public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) {
            return state.isOf(Blocks.NETHER_PORTAL) ? false : super.canDestroyBlock(explosion, world, pos, state, power);
        }

        @Override
        public Optional<Float> getBlastResistance(Explosion explosion, BlockView world, BlockPos pos, BlockState blockState, FluidState fluidState) {
            return blockState.isOf(Blocks.NETHER_PORTAL) ? Optional.empty() : super.getBlastResistance(explosion, world, pos, blockState, fluidState);
        }
    };

    private void explode() {
        float f = 4.0F;
        this.getWorld()
                .createExplosion(
                        this,
                        Explosion.createDamageSource(this.getWorld(), this),
                        this.teleported ? TELEPORTED_EXPLOSION_BEHAVIOR : null,
                        this.getX(),
                        this.getBodyY(0.0625),
                        this.getZ(),
                        2.0F,
                        true,
                        World.ExplosionSourceType.TNT
                );
    }
}
