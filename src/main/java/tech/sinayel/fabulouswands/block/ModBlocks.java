package tech.sinayel.fabulouswands.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import tech.sinayel.fabulouswands.FabulousWands;

public class ModBlocks {
    public static final Block NOBLE_PLANK = registerBlocks(
            "noble_plank",
            new Block(
                    AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()
            )
    );

    // Une bûche est un PillarBlock : elle s'oriente selon le sens où on la pose.
    public static final Block NOBLE_LOG = registerBlocks(
            "noble_log",
            new PillarBlock(
                    AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()
            )
    );

    // Table de craft des baguettes (pour l'instant un simple bloc décoratif, sans interface).
    public static final Block WANDCRAFT_TABLE = registerBlocks(
            "wandcraft_table",
            new Block(
                    AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(NoteBlockInstrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()
            )
    );

    public static final Block NEW_BLOCK = registerBlocks("new_block", new Block(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN
    ).instrument(NoteBlockInstrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));

    private static Block registerBlocks(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(FabulousWands.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(FabulousWands.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        FabulousWands.LOGGER.info("Registering mod block for " + FabulousWands.MOD_ID);
    }
}
