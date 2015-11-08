package com.teammetallurgy.agriculture.recpies;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class RecipeCounter
{
    private ArrayList<ArrayList<ItemStack>> ingredients;
    private ItemStack output;

    public RecipeCounter(ItemStack output, ItemStack... ingredients)
    {
        this.output = output;

        this.ingredients = new ArrayList<ArrayList<ItemStack>>();
        for (ItemStack ingredient : ingredients)
        {
            ArrayList<ItemStack> innerList = new ArrayList<ItemStack>();
            innerList.add(ingredient);
            this.ingredients.add(innerList);
        }
    }

    public RecipeCounter(ItemStack output, ArrayList<ArrayList<ItemStack>> ingredients)
    {
        this.output = output;
        this.ingredients = ingredients;
    }

    public ItemStack getOutput()
    {
        return output.copy();
    }

    public ArrayList<ArrayList<ItemStack>> getIngredients()
    {
        return ingredients;
    }
}
