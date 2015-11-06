package com.teammetallurgy.agriculture.handler;

import java.util.List;

import net.minecraft.item.ItemStack;

import com.teammetallurgy.agriculture.ItemList;

import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent.MissingMapping;

public class MissingMapHandler
{
    public static void processingMissingMap(FMLMissingMappingsEvent event)
    {
        List<MissingMapping> missingMappings = event.get();
        for (MissingMapping map : missingMappings)
        {
            if (map.name.equals("Agriculture:base.crop"))
            {
                ItemStack stack = ItemList.getItemStack("base", "Dough");
                map.remap(stack.getItem());
            }
        }
    }
}
