package com.teammetallurgy.agriculture.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.oredict.OreDictionary;

public class AgricultureEventHandler
{
    @SubscribeEvent
    public void displayItemTooltip(ItemTooltipEvent event)
    {
        if (!event.showAdvancedItemTooltips || !AgricultureConfigHandler.EXTRA_TOOLTIPS)
            return;
        
        UniqueIdentifier uid;
        if (event.itemStack.getItem() instanceof ItemBlock)
        {
            Block block = Block.getBlockFromItem(event.itemStack.getItem());
            uid = GameRegistry.findUniqueIdentifierFor(block);
        }
        else 
        {
            uid = GameRegistry.findUniqueIdentifierFor(event.itemStack.getItem());
        }
       
        
        event.toolTip.add(StatCollector.translateToLocal("tooltip.agriculture.namedId") + ": " + uid.toString());
        int [] oreIds = OreDictionary.getOreIDs(event.itemStack);
        if (oreIds.length > 0)
        {
            event.toolTip.add(StatCollector.translateToLocal("tooltip.agriculture.oredic") + ":");
            for (int oreId : oreIds)
            {
                event.toolTip.add(EnumChatFormatting.ITALIC + " " + OreDictionary.getOreName(oreId) + EnumChatFormatting.RESET);
            }
        }
    }
}
