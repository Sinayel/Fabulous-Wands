package tech.sinayel.fabulouswands.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import tech.sinayel.fabulouswands.FabulousWands;

public class ModEntities {
    public static final EntityType<SpellEntity> SPELL = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(FabulousWands.MOD_ID, "spell"),
            EntityType.Builder.<SpellEntity>create(SpellEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25F, 0.25F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(10)
                    .build("spell")
    );

    public static void registerModEntities() {
        FabulousWands.LOGGER.info("Registering mod entities for " + FabulousWands.MOD_ID);
    }
}
