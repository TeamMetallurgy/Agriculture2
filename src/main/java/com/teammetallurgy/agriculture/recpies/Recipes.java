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
        String[] ingredientsOreDic = new String[food.recipe.length];

        for (int i = 0; i < ingredientsOreDic.length; i++)
        {
            Food ingredient = foodSet.getFoodInfo(food.recipe[i]);

            if (ingredient != null)
            {
                // Found a set ingredient

                String tag = ingredient.getName().replace(" ", "");

                String oreDicPrefix = "crop";

                if (ingredient.type == FoodType.base || ingredient.type == FoodType.edible)
                {
                    oreDicPrefix = "food";
                }

                ingredientsOreDic[i] = oreDicPrefix + tag;
                continue;
            }

            // Find from predefined
            String oreDicName = getPredefinedOreDicName(food.recipe[i]);
            if (oreDicName != null)
            {
                ingredientsOreDic[i] = oreDicName;
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
                addOreDicProcessRecipe(foodSet.getItemStack(food.getName()), ingredientsOreDic);
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

    public static void addProcessorRecipe(ItemStack output, ItemStack... ingredients)
    {
        if (ingredients.length == 1 && ingredients[0] != null)
        {
            processorRecipes.add(new RecipeProcessor(ingredients[0], output));
        }
        else if (ingredients.length == 2 && ingredients[0] != null && ingredients[1] != null)
        {
            processorRecipes.add(new RecipeProcessor(ingredients[0], ingredients[1], output));
        }
    }

    public static void addOreDicProcessRecipe(ItemStack output, String... ingredients)
    {
        if (ingredients.length == 1 && ingredients[0] != null)
        {
            ArrayList<ItemStack> oreDicIngredientList = OreDictionary.getOres(ingredients[0]);
            if (oreDicIngredientList != null && oreDicIngredientList.size() > 0)
            {
                ItemStack[] oreDicIngreadient = oreDicIngredientList.toArray(new ItemStack[oreDicIngredientList.size()]);
                processorRecipes.add(new RecipeProcessor(oreDicIngreadient, output));
            }
        }
        else if (ingredients.length == 2 && ingredients[0] != null && ingredients[1] != null)
        {
            ArrayList<ItemStack> oreDicIngredientList1 = OreDictionary.getOres(ingredients[0]);
            ArrayList<ItemStack> oreDicIngredienttList2 = OreDictionary.getOres(ingredients[1]);

            if (oreDicIngredientList1 != null && oreDicIngredientList1.size() > 0 && oreDicIngredienttList2 != null && oreDicIngredienttList2.size() > 0)
            {
                ItemStack[] oreDicIngredient1 = oreDicIngredientList1.toArray(new ItemStack[oreDicIngredientList1.size()]);
                ItemStack[] oreDicIngredient2 = oreDicIngredienttList2.toArray(new ItemStack[oreDicIngredienttList2.size()]);

                processorRecipes.add(new RecipeProcessor(oreDicIngredient1, oreDicIngredient2, output));
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
