package com.teammetallurgy.agriculture.recpies;

import java.util.ArrayList;
import java.util.HashMap;

import com.teammetallurgy.agriculture.ItemList;
import com.teammetallurgy.agriculture.food.Food;
import com.teammetallurgy.agriculture.food.FoodSet;
import com.teammetallurgy.agriculture.food.Food.FoodType;
import com.teammetallurgy.agriculture.handler.AgricultureLogHandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class Recipes
{
    private static HashMap<String, String> predefinedOreDicNames = new HashMap<String, String>();
    private static ArrayList<RecipeProcessor> processorRecipes = new ArrayList<RecipeProcessor>();

    public static void create()
    {
        initPredefinedOreDicNames();

        String[] setNames = ItemList.getSetNames();
        for (String setName : setNames)
        {
            FoodSet foodSet = ItemList.getSet(setName);

            Food[] foods = foodSet.getFoods();

            for (Food food : foods)
            {
                if (food != null)
                {
                    if (food.method != null)
                    {
                        addOreDicRecipe(foodSet, food);
                    }
                }
                else
                {
                    AgricultureLogHandler.warn("issue with food");
                }
            }
        }

        AgricultureLogHandler.trace("Finished creating recipes");
    }

    private static void addOreDicRecipe(FoodSet foodSet, Food food)
    {
        String[] ingreadientsOreDic = new String[food.recipe.length];

        for (int i = 0; i < ingreadientsOreDic.length; i++)
        {
            Food ingreadient = foodSet.getFoodInfo(food.recipe[i]);

            if (ingreadient != null)
            {
                // Found a set ingreadient

                String tag = ingreadient.getName().replace(" ", "");

                String oreDicPrefix = "crop";

                if (ingreadient.type == FoodType.base || ingreadient.type == FoodType.edible)
                {
                    oreDicPrefix = "food";
                }

                ingreadientsOreDic[i] = oreDicPrefix + tag;
                continue;
            }

            // Find from predefined
            String oreDicName = getPredefinedOreDicName(food.recipe[i]);
            if (oreDicName != null)
            {
                ingreadientsOreDic[i] = oreDicName;
                continue;
            }

            AgricultureLogHandler.info("Couldn't find ingreadiant '" + food.recipe[i] + "' for '" + food.getName() + "'" );
            return;
        }

        switch (food.method)
        {
            case bake:
                break;
            case brew:
                break;
            case freeze:
                break;
            case fry:
                break;
            case prepare:
                break;
            case process:
                addOreDicProcessRecipe(foodSet.getItemStack(food.getName()), ingreadientsOreDic);
                break;
            default:
                break;

        }
    }

    private static void initPredefinedOreDicNames()
    {
        predefinedOreDicNames.put("Carrot", "cropCarrot");
        predefinedOreDicNames.put("Potato", "cropPotato");
        predefinedOreDicNames.put("Stick", "stickWood");
        predefinedOreDicNames.put("Water", "foodCupOfWater");
        predefinedOreDicNames.put("Wheat", "cropWheat");
    }

    public static String getPredefinedOreDicName(String name)
    {
        return predefinedOreDicNames.get(name);
    }

    public static void addProcessorRecipe(ItemStack output, ItemStack... ingreadients)
    {
        if (ingreadients.length == 1 && ingreadients[0] != null)
        {
            processorRecipes.add(new RecipeProcessor(ingreadients[0], output));
        }
        else if (ingreadients.length == 2 && ingreadients[0] != null && ingreadients[1] != null)
        {
            processorRecipes.add(new RecipeProcessor(ingreadients[0], ingreadients[1], output));
        }
    }

    public static void addOreDicProcessRecipe(ItemStack output, String... ingreadients)
    {
        if (ingreadients.length == 1 && ingreadients[0] != null)
        {
            ArrayList<ItemStack> oreDicIngreadientList = OreDictionary.getOres(ingreadients[0]);
            if (oreDicIngreadientList != null && oreDicIngreadientList.size() > 0)
            {
                ItemStack[] oreDicIngreadient = oreDicIngreadientList.toArray(new ItemStack[oreDicIngreadientList.size()]);
                processorRecipes.add(new RecipeProcessor(oreDicIngreadient, output));
            }
        }
        else if (ingreadients.length == 2 && ingreadients[0] != null && ingreadients[1] != null)
        {
            ArrayList<ItemStack> oreDicIngreadientList1 = OreDictionary.getOres(ingreadients[0]);
            ArrayList<ItemStack> oreDicIngreadientList2 = OreDictionary.getOres(ingreadients[1]);

            if (oreDicIngreadientList1 != null && oreDicIngreadientList1.size() > 0 && oreDicIngreadientList2 != null && oreDicIngreadientList2.size() > 0)
            {
                ItemStack[] oreDicIngreadient1 = oreDicIngreadientList1.toArray(new ItemStack[oreDicIngreadientList1.size()]);
                ItemStack[] oreDicIngreadient2 = oreDicIngreadientList2.toArray(new ItemStack[oreDicIngreadientList2.size()]);

                processorRecipes.add(new RecipeProcessor(oreDicIngreadient1, oreDicIngreadient2, output));
            }
        }
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
