package net.tailored;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.tailored.datagen.TailoredBlockLootTableProvider;
import net.tailored.datagen.TailoredItemTagProvider;

public class TailoredDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(TailoredBlockLootTableProvider::new);
		pack.addProvider(TailoredItemTagProvider::new);
	}
}
