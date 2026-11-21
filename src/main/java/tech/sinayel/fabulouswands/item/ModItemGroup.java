package tech.sinayel.fabulouswands.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import tech.sinayel.fabulouswands.FabulousWands;
import tech.sinayel.fabulouswands.block.ModBlocks;

public class ModItemGroup {
    public static final ItemGroup FABULOUSWANDS_WANDS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(FabulousWands.MOD_ID, "fabulouswands_wand"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.WAND))
                    .displayName(Text.translatable("itemgroup.fabulouswands.fabulouswands_wand"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.WAND);
                    }).build());

    public static final ItemGroup FABULOUSWANDS_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(FabulousWands.MOD_ID, "fabulouswands_item"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.NOBLE_STICK))
                    .displayName(Text.translatable("itemgroup.fabulouswands.fabulouswands_item"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.NOBLE_STICK);
                        entries.add(ModItems.TAME_LEATHER);
                    }).build());

    public static final ItemGroup FABULOUSWANDS_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(FabulousWands.MOD_ID, "fabulouswands_block"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.NOBLE_LOG))
                    .displayName(Text.translatable("itemgroup.fabulouswands.fabulouswands_block"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.NOBLE_LOG);
                        entries.add(ModBlocks.NOBLE_PLANK);
                        entries.add(ModBlocks.WANDCRAFT_TABLE);
                    }).build());

    public static void registerItemGroups(){
        FabulousWands.LOGGER.info("Register Item Group for : " + FabulousWands.MOD_ID);
    }
}
