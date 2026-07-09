package net.tailored.common.register;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.tailored.Tailored;
import net.tailored.common.block.RugBlock;

import java.util.function.Function;

public class TailoredBlocks {

    public static final EnumProperty<RugBlock.ConnectMode> CONNECT_MODE = EnumProperty.create("connect_mode" , RugBlock.ConnectMode.class);


    public static final Block PINK_RUG = registerBlock("pink_rug" , RugBlock::new ,BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_CARPET));
    public static final Block MAGENTA_RUG = registerBlock("magenta_rug" , RugBlock::new ,BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_CARPET));
    public static final Block PURPLE_RUG = registerBlock("purple_rug" , RugBlock::new ,BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_CARPET));
    public static final Block BLUE_RUG = registerBlock("blue_rug" , RugBlock::new ,BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_CARPET));
    public static final Block CYAN_RUG = registerBlock("cyan_rug" , RugBlock::new ,BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_CARPET));
    public static final Block LIGHT_BLUE_RUG = registerBlock("light_blue_rug" , RugBlock::new ,BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_CARPET));
    public static final Block LIME_RUG = registerBlock("lime_rug" , RugBlock::new ,BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_CARPET));
    public static final Block GREEN_RUG = registerBlock("green_rug" , RugBlock::new ,BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_CARPET));
    public static final Block YELLOW_RUG = registerBlock("yellow_rug" , RugBlock::new ,BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_CARPET));
    public static final Block ORANGE_RUG = registerBlock("orange_rug" , RugBlock::new ,BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_CARPET));
    public static final Block RED_RUG = registerBlock("red_rug" , RugBlock::new ,BlockBehaviour.Properties.ofFullCopy(Blocks.RED_CARPET));
    public static final Block BROWN_RUG = registerBlock("brown_rug" , RugBlock::new ,BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_CARPET));
    public static final Block BLACK_RUG = registerBlock("black_rug" , RugBlock::new ,BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET));
    public static final Block GRAY_RUG = registerBlock("gray_rug" , RugBlock::new ,BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_CARPET));
    public static final Block LIGHT_GRAY_RUG = registerBlock("light_gray_rug" , RugBlock::new ,BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_CARPET));
    public static final Block WHITE_RUG = registerBlock("white_rug" , RugBlock::new ,BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CARPET));




    public static Block registerBlock(String id, Function<BlockBehaviour.Properties, Block> factory , BlockBehaviour.Properties properties) {
        Block toRegister = factory.apply(properties.setId(getBlockKey(id)));
        registerBlockItem(id, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(Tailored.MOD_ID, id), toRegister);
    }

    public static void registerBlockItem(String id, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Tailored.MOD_ID, id),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(TailoredItems.getItemKey(id))));
    }

    public static ResourceKey<Block> getBlockKey(String id){
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Tailored.MOD_ID , id));
    }

    public static void init(){}
}
