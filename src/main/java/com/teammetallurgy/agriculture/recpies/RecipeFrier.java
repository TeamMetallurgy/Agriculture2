package com.teammetallurgy.agriculture.recpies;

import net.minecraft.item.ItemStack;

public class RecipeFrier
{
    private ItemStack[] ingredients;
    private ItemStack output;
    private int temp;

    public RecipeFrier(ItemStack ingredient, int temp, ItemStack output)
    {
        this.ingredients = new ItemStack[] { ingredient };
        this.temp = temp;
        this.output = output;
    }

    public RecipeFrier(ItemStack[] ingredients, int temp, ItemStack output)
    {
        this.ingredients = ingredients;
        this.temp = temp;
        this.output = output;
    }

    public ItemStack[] getIngredients()
    {
        return ingredients;
    }

    public int getTemp()
    {
        return temp;
    }

    public ItemStack getOutput()
    {
        return output.copy();
    }
}
