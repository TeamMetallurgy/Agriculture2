package com.teammetallurgy.agriculture.recpies;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class Recipes
{
    private static ArrayList<RecipeProcessor> processorRecipes = new ArrayList<RecipeProcessor>();

    public static void create()
    {
        // TODO: Rewrite
    }

    public static ItemStack getProcessorRecipeOutput(ItemStack... ingredents)
    {
        // TODO: Rewrite
        return null;
    }

    public static RecipeProcessor[] getProcessorRecipes()
    {
        RecipeProcessor[] recipes = new RecipeProcessor[processorRecipes.size()];
        recipes = processorRecipes.toArray(recipes);
        return recipes;
    }

}
