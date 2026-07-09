package net.tailored.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.tailored.common.register.TailoredBlocks;

import java.util.concurrent.CompletableFuture;

public class TailoredBlockLootTableProvider extends FabricBlockLootTableProvider {
    public TailoredBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(TailoredBlocks.PINK_RUG);
        dropSelf(TailoredBlocks.MAGENTA_RUG);
        dropSelf(TailoredBlocks.PURPLE_RUG);
        dropSelf(TailoredBlocks.BLUE_RUG);
        dropSelf(TailoredBlocks.CYAN_RUG);
        dropSelf(TailoredBlocks.LIGHT_BLUE_RUG);
        dropSelf(TailoredBlocks.LIME_RUG);
        dropSelf(TailoredBlocks.GREEN_RUG);
        dropSelf(TailoredBlocks.YELLOW_RUG);
        dropSelf(TailoredBlocks.ORANGE_RUG);
        dropSelf(TailoredBlocks.RED_RUG);
        dropSelf(TailoredBlocks.BROWN_RUG);
        dropSelf(TailoredBlocks.BLACK_RUG);
        dropSelf(TailoredBlocks.GRAY_RUG);
        dropSelf(TailoredBlocks.LIGHT_GRAY_RUG);
        dropSelf(TailoredBlocks.WHITE_RUG);
    }
}
