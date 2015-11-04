package com.teammetallurgy.agriculture.utils;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AgricultureCreativeTab extends CreativeTabs
{
    private ItemStack iconItemStack;

    public AgricultureCreativeTab(String lable)
    {
        super(lable);
    }

    @Override
    public Item getTabIconItem()
    {
        return Items.stick;
    }
    
    @Override
    public ItemStack getIconItemStack()
    {
        if (iconItemStack == null)
        {
            iconItemStack = new ItemStack(getTabIconItem());
        }
        return iconItemStack;
    }
    
    public void setItemStack(ItemStack itemStack)
    {
        iconItemStack = itemStack;
    }
    
    public void setItem(Block block)
    {
        iconItemStack = new ItemStack(block);
    }

}
