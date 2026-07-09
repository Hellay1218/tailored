package net.tailored.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.tailored.common.register.TailoredItems;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TailoredItemTagProvider extends FabricTagProvider.ItemTagProvider  {

    public TailoredItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(ItemTags.SWORDS)
                .add(TailoredItems.SCISSORS);
    }
}
