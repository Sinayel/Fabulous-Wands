package tech.sinayel.fabulouswands;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.sinayel.fabulouswands.block.ModBlocks;
import tech.sinayel.fabulouswands.entity.ModEntities;
import tech.sinayel.fabulouswands.item.ModItemGroup;
import tech.sinayel.fabulouswands.item.ModItems;

public class FabulousWands implements ModInitializer {
	public static final String MOD_ID = "fabulouswands";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEntities.registerModEntities();
	}
}
