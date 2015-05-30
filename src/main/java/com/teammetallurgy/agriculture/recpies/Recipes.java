package com.teammetallurgy.agriculture.recpies;

import java.util.ArrayList;

import com.teammetallurgy.agriculture.Agriculture;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class Recipes
{
    private static ArrayList<String[]> tempOreDicProcessorRecipes = new ArrayList<String[]>();

    private static ArrayList<RecipeProcessor> processorRecipes = new ArrayList<RecipeProcessor>();

    public static void addProcessorRecipeOreDic(String ingredient1, String ingredient2, String output)
    {
        tempOreDicProcessorRecipes.add(new String[] { ingredient1, ingredient2, output });
    }

    public static void createRecipes()
    {
        // Processor recipes
        for (String[] tempProcessorRecipe : tempOreDicProcessorRecipes)
        {

            ItemStack ingredient1 = null;
            ItemStack ingredient2 = null;
            ItemStack output = null;

            ArrayList<ItemStack> ingredient1List = OreDictionary.getOres(tempProcessorRecipe[0]);
            ArrayList<ItemStack> ingredient2List = OreDictionary.getOres(tempProcessorRecipe[1]);
            ArrayList<ItemStack> outputList = OreDictionary.getOres(tempProcessorRecipe[2]);

            if (ingredient1List != null && ingredient1List.size() > 0 && ingredient1List.get(0) != null) ingredient1 = ingredient1List.get(0).copy();
            if (ingredient2List != null && ingredient2List.size() > 0 && ingredient2List.get(0) != null) ingredient2 = ingredient2List.get(0).copy();
            if (outputList != null && outputList.size() > 0 && outputList.get(0) != null) output = outputList.get(0).copy();

            if (ingredient1 == null || (ingredient2 == null && !tempProcessorRecipe[1].equalsIgnoreCase("")) || output == null)
            {
                String recipe = tempProcessorRecipe[0];
                if (!tempProcessorRecipe[1].equalsIgnoreCase(""))
                {
                    recipe += " + " + tempProcessorRecipe[1];
                }
                recipe +=  " --> " + tempProcessorRecipe[2];
                
                Agriculture.logger.error("processing recipes: ingredients or output not found for recipe: " + recipe);
                
                continue;
            }
            
            if (tempProcessorRecipe[1].equalsIgnoreCase(""))  processorRecipes.add(new RecipeProcessor(ingredient1, output));
            else processorRecipes.add(new RecipeProcessor(ingredient1, ingredient2, output));

        }
        tempOreDicProcessorRecipes.clear();
        tempOreDicProcessorRecipes = null;

    }

    public static ItemStack getProcessorRecipeOutput(ItemStack... ingredents)
    {
        for (RecipeProcessor recipe : processorRecipes)
        {
            if (recipe.isInputMatch(ingredents)) return recipe.getOutput();
        }

        String inputUnlocalizedName = "";

        for (ItemStack ingredent : ingredents)
        {
            if (ingredent == null) continue;

            if (inputUnlocalizedName.isEmpty()) inputUnlocalizedName = ingredent.getUnlocalizedName();
            else inputUnlocalizedName += ", " + ingredent.getUnlocalizedName();
        }

        return null;
    }

    public static RecipeProcessor[] getProcessorRecipes()
    {
        RecipeProcessor[] recipes = new RecipeProcessor[processorRecipes.size()];
        recipes = processorRecipes.toArray(recipes);
        return recipes;
    }

}
