package tech.sinayel.fabulouswands;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import tech.sinayel.fabulouswands.datagen.ModEnglishLangProvider;
import tech.sinayel.fabulouswands.datagen.ModModelProvider;
import tech.sinayel.fabulouswands.datagen.ModRecipeProvider;

public class FabulousWandsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModEnglishLangProvider::new);
	}
}
