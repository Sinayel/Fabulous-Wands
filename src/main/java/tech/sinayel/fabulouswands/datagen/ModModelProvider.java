package tech.sinayel.fabulouswands.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import tech.sinayel.fabulouswands.block.ModBlocks;
import tech.sinayel.fabulouswands.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // block simple, same texture on all faces
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.NOBLE_PLANK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WANDCRAFT_TABLE);

        // Wood, different angle
        blockStateModelGenerator.registerLog(ModBlocks.NOBLE_LOG).log(ModBlocks.NOBLE_LOG);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.TAME_LEATHER, Models.GENERATED);
        itemModelGenerator.register(ModItems.WAND, Models.GENERATED);
    }
}