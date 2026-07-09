package net.tailored.common.register;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.tailored.Tailored;
import net.tailored.common.item.ScissorsItem;

import java.util.function.Function;

public class TailoredItems {

    public static final Item SCISSORS = registerItem("scissors" , ScissorsItem::new , new Item.Properties().sword(ToolMaterial.IRON , 3.0F, -2.4F).stacksTo(1));

    public static Item registerItem(String id , Function<Item.Properties, Item> factory , Item.Properties properties){
        Item item = (Item)factory.apply(properties.setId(getItemKey(id)));
        return Registry.register(BuiltInRegistries.ITEM , Identifier.fromNamespaceAndPath(Tailored.MOD_ID , id) , item);
    }

    public static ResourceKey<Item> getItemKey(String id){
        return ResourceKey.create(Registries.ITEM,Identifier.fromNamespaceAndPath(Tailored.MOD_ID , id));
    }

    public static void init(){}
}
