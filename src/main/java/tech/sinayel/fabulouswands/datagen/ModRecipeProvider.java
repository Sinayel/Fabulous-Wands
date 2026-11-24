package tech.sinayel.fabulouswands.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import tech.sinayel.fabulouswands.FabulousWands;
import tech.sinayel.fabulouswands.block.ModBlocks;
import tech.sinayel.fabulouswands.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        // Items
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TAME_LEATHER)
                .pattern("##")
                .input('#', Items.LEATHER)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NOBLE_STICK, 2)
                .pattern("#")
                .pattern("#")
                .input('#', ModBlocks.NOBLE_PLANK)
                .criterion(hasItem(ModBlocks.NOBLE_PLANK), conditionsFromItem(ModBlocks.NOBLE_PLANK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MAGIC_BOOK)
                .pattern("LBL")
                .input('B', Items.BOOK)
                .input('L', ModItems.TAME_LEATHER)
                .criterion(hasItem(Items.BOOK), conditionsFromItem(Items.BOOK))
                .criterion(hasItem(ModItems.TAME_LEATHER), conditionsFromItem(ModItems.TAME_LEATHER))
                .offerTo(exporter, Identifier.of(FabulousWands.MOD_ID, "magic_book_from_leather"));

        // Blocks
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NOBLE_PLANK)
                .pattern("#")
                .input('#', ModBlocks.NOBLE_LOG)
                .criterion(hasItem(ModBlocks.NOBLE_LOG), conditionsFromItem(ModBlocks.NOBLE_LOG))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WANDCRAFT_TABLE)
                .pattern("LL")
                .pattern("PP")
                .input('P', ModBlocks.NOBLE_PLANK)
                .input('L', ModItems.TAME_LEATHER)
                .criterion(hasItem(ModBlocks.NOBLE_PLANK), conditionsFromItem(ModBlocks.NOBLE_PLANK))
                .criterion(hasItem(ModItems.TAME_LEATHER), conditionsFromItem(ModItems.TAME_LEATHER))
                .offerTo(exporter);
    }
}