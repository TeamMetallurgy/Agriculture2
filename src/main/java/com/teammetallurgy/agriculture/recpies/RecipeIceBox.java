package com.teammetallurgy.agriculture.recpies;

import net.minecraft.item.ItemStack;

public class RecipeIceBox
{
    private ItemStack[] ingredients;
    private int temp;
    private ItemStack output;

    public RecipeIceBox(ItemStack ingredient, int temp, ItemStack output)
    {
        this.ingredients = new ItemStack[] { ingredient };
        this.temp = temp;
        this.output = output;
    }

    public RecipeIceBox(ItemStack[] ingredients, int temp, ItemStack output)
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
        return output;
    }

}
