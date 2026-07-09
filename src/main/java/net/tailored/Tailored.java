package net.tailored;

import net.fabricmc.api.ModInitializer;

import net.tailored.common.register.TailoredBlocks;
import net.tailored.common.register.TailoredItemGroups;
import net.tailored.common.register.TailoredItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tailored implements ModInitializer {
	public static final String MOD_ID = "tailored";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		TailoredItems.init();
		TailoredBlocks.init();
		TailoredItemGroups.init();
		//LOGGER.info("Tailoring evil plans!");
	}
}