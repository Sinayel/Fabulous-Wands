package tech.sinayel.fabulouswands.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import tech.sinayel.fabulouswands.FabulousWands;

public class ModItems {
    public static final Item TEST_FIRST_OBJECT = registerItems("test_item", new Item(new Item.Settings()));
    public static final Item SECOND_FIRST_OBJECT = registerItems("second_item", new Item(new Item.Settings()));

    public static Item registerItems(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(FabulousWands.MOD_ID, name), item);
    }

    public static void registerModItems() {
        FabulousWands.LOGGER.info("Registering mod item for " + FabulousWands.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(TEST_FIRST_OBJECT);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(SECOND_FIRST_OBJECT);
        });
    }
}
