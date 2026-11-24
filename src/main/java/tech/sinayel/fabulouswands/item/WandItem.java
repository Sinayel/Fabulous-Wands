package tech.sinayel.fabulouswands.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import tech.sinayel.fabulouswands.entity.SpellEntity;

public class WandItem extends Item implements ProjectileItem {
    public WandItem(Item.Settings settings) { super(settings); }
    /**
     * Use(): First implementation of shooting with the wand
     * Play sound and throw Wand
     * @param world  The overworld
     * @param user The user
     * @param hand The hand of the player
     * @return Success if it success
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT,
                SoundCategory.NEUTRAL,
                0.8F,
                0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        if (!world.isClient) {
            SpellEntity wandEntity = new SpellEntity(world, user);
            wandEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            wandEntity.setNoGravity(true);
            world.spawnEntity(wandEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
//        user.getItemCooldownManager().set(this, 100); // default cooldown = 5sec
        // itemStack.decrement(1); // destroy your wand
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        return new SpellEntity(world, pos.getX(), pos.getY(), pos.getZ());
    }
}
