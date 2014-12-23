package com.teammetallurgy.agriculture.recpies;

import net.minecraft.item.ItemStack;

public interface IAgricultureRecipe
{
    public boolean isInputMatch(ItemStack... itemStack);

    public ItemStack getOutput();
}
