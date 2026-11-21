package tech.sinayel.fabulouswands.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import tech.sinayel.fabulouswands.FabulousWands;

public class ModItems {

    public static final Item WAND = registerItems("wand", new WandItem(new Item.Settings().maxCount(1)));
    public static final Item NOBLE_STICK = registerItems("noble_stick", new Item(new Item.Settings()));
    public static final Item TAME_LEATHER = registerItems("tame_leather", new Item(new Item.Settings()));

    public static Item registerItems(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(FabulousWands.MOD_ID, name), item);
    }

    public static void registerModItems() {
        FabulousWands.LOGGER.info("Registering mod item for " + FabulousWands.MOD_ID);
    }
}
