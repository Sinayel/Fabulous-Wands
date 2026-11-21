package tech.sinayel.fabulouswands.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import tech.sinayel.fabulouswands.block.ModBlocks;
import tech.sinayel.fabulouswands.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModEnglishLangProvider extends FabricLanguageProvider {
    public ModEnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        // Items
        translationBuilder.add(ModItems.TAME_LEATHER, "Tame Leather");
        translationBuilder.add(ModItems.WAND, "Wand");
        translationBuilder.add(ModItems.NOBLE_STICK, "Noble Stick");

        // Blocks
        translationBuilder.add(ModBlocks.NOBLE_LOG, "Noble Log");
        translationBuilder.add(ModBlocks.NOBLE_PLANK, "Noble Plank");
        translationBuilder.add(ModBlocks.WANDCRAFT_TABLE, "WandCraft Table");

        // Item groups
        translationBuilder.add("itemgroup.fabulouswands.fabulouswands_wand", "Fabulous Wands");
        translationBuilder.add("itemgroup.fabulouswands.fabulouswands_item", "Fabulous Items");
        translationBuilder.add("itemgroup.fabulouswands.fabulouswands_block", "Fabulous Wands Blocks");
    }
}