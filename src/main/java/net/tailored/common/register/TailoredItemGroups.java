package net.tailored.common.register;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.tailored.Tailored;

public class TailoredItemGroups {

    public static final ResourceKey<CreativeModeTab> TAILORED_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(Tailored.MOD_ID, "tailored_tab")
    );
    public static final CreativeModeTab TAILORED_CREATIVE_TAB = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TailoredItems.SCISSORS))
            .title(Component.translatable("itemgroup.tailored_tab"))
            .displayItems(((itemDisplayParameters, output) -> {

                output.accept(TailoredItems.SCISSORS);


                output.accept(TailoredBlocks.PINK_RUG);
                output.accept(TailoredBlocks.MAGENTA_RUG);
                output.accept(TailoredBlocks.PURPLE_RUG);
                output.accept(TailoredBlocks.BLUE_RUG);
                output.accept(TailoredBlocks.CYAN_RUG);
                output.accept(TailoredBlocks.LIGHT_BLUE_RUG);
                output.accept(TailoredBlocks.LIME_RUG);
                output.accept(TailoredBlocks.GREEN_RUG);
                output.accept(TailoredBlocks.YELLOW_RUG);
                output.accept(TailoredBlocks.ORANGE_RUG);
                output.accept(TailoredBlocks.RED_RUG);
                output.accept(TailoredBlocks.BROWN_RUG);
                output.accept(TailoredBlocks.BLACK_RUG);
                output.accept(TailoredBlocks.GRAY_RUG);
                output.accept(TailoredBlocks.LIGHT_GRAY_RUG);
                output.accept(TailoredBlocks.WHITE_RUG);

            }))

            .build();

    public static void init(){
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TAILORED_TAB_KEY, TAILORED_CREATIVE_TAB);
    }



}
