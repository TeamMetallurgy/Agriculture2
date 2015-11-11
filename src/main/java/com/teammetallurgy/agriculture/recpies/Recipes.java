package com.teammetallurgy.agriculture.recpies;

import java.util.ArrayList;
import java.util.HashMap;

import com.teammetallurgy.agriculture.ItemList;
import com.teammetallurgy.agriculture.food.Food;
import com.teammetallurgy.agriculture.food.FoodSet;
import com.teammetallurgy.agriculture.food.Food.FoodType;
import com.teammetallurgy.agriculture.handler.AgricultureLogHandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.oredict.OreDictionary;

public class Recipes
{
    private static HashMap<String, String> predefinedOreDicNames = new HashMap<String, String>();
    private static ArrayList<RecipeBrewer> brewerRecipes = new ArrayList<RecipeBrewer>();
    private static ArrayList<RecipeCoolent> coolentRecipes = new ArrayList<RecipeCoolent>();
    private static ArrayList<RecipeCounter> counterRecipes = new ArrayList<RecipeCounter>();
    private static ArrayList<RecipeFrier> frierRecipes = new ArrayList<RecipeFrier>();
    private static ArrayList<RecipeIceBox> iceBoxRecipes = new ArrayList<RecipeIceBox>();
    private static ArrayList<RecipeOven> ovenRecipes = new ArrayList<RecipeOven>();
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

            AgricultureLogHandler.info("Couldn't find ingreadiant '" + food.recipe[i] + "' for '" + food.getName() + "'");
            return;
        }

        switch (food.method)
        {
            case bake:
                addOreDicOvenRecipe(foodSet.getItemStack(food.getName()), ingredientsOreDic[0]);
                break;
            case brew:
                break;
            case freeze:
                addOreDicIceBoxRecipe(foodSet.getItemStack(food.getName()), ingredientsOreDic[0]);
                break;
            case fry:
                addOreDicFrierRecipe(foodSet.getItemStack(food.getName()), ingredientsOreDic[0]);
                break;
            case prepare:
                addOreDicCounterRecipe(foodSet.getItemStack(food.getName()), ingredientsOreDic);
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

    public static void addBrewerRecipe(String outputFluid, String baseFluid, ItemStack ingredient)
    {
        FluidStack output = FluidRegistry.getFluidStack(outputFluid, FluidContainerRegistry.BUCKET_VOLUME);
        FluidStack base = FluidRegistry.getFluidStack(baseFluid, FluidContainerRegistry.BUCKET_VOLUME);

        if (output != null && base != null)
        {
            brewerRecipes.add(new RecipeBrewer(ingredient, base, output));
        }
    }

    public static void addOreDicBrewerRecipe(String outputFluid, String baseFluid, String ingredient)
    {
        FluidStack output = FluidRegistry.getFluidStack(outputFluid, FluidContainerRegistry.BUCKET_VOLUME);
        FluidStack base = FluidRegistry.getFluidStack(baseFluid, FluidContainerRegistry.BUCKET_VOLUME);
        ArrayList<ItemStack> ingredientList = OreDictionary.getOres(ingredient);

        if (output != null && base != null && ingredientList != null && ingredientList.size() > 0)
        {
            ItemStack[] ingredients = ingredientList.toArray(new ItemStack[ingredientList.size()]);
            brewerRecipes.add(new RecipeBrewer(ingredients, base, output));
        }
    }

    public static void addCoolentRecipe(ItemStack coolent, int ticks, int temp)
    {
        coolentRecipes.add(new RecipeCoolent(coolent, ticks, temp));
    }

    public static void addOreDicCoolentRecipe(String coolent, int ticks, int temp)
    {
        ArrayList<ItemStack> coolentList = OreDictionary.getOres(coolent);
        if (coolentList != null && coolentList.size() > 0)
        {
            ItemStack[] coolents = coolentList.toArray(new ItemStack[coolentList.size()]);
            coolentRecipes.add(new RecipeCoolent(coolents, ticks, temp));
        }
    }

    public static void addCounterRecipe(ItemStack output, ItemStack... ingredients)
    {
        counterRecipes.add(new RecipeCounter(output, ingredients));
    }

    public static void addOreDicCounterRecipe(ItemStack output, String... ingredients)
    {
        ArrayList<ArrayList<ItemStack>> allIngredientsList = new ArrayList<ArrayList<ItemStack>>();
        for (String ingredient : ingredients)
        {
            ArrayList<ItemStack> ingredientsList = OreDictionary.getOres(ingredient);
            if (ingredientsList == null || ingredientsList.size() <= 0)
            {
                AgricultureLogHandler.warn("Couldn't find oredic ingredient '" + ingredient + "' for " + output.getUnlocalizedName() + " counter recipe, skipping...");
                return;
            }
            allIngredientsList.add(ingredientsList);
        }

        counterRecipes.add(new RecipeCounter(output, allIngredientsList));
    }

    public static RecipeCounter[] getCounterRecipes()
    {
        RecipeCounter[] recipe = new RecipeCounter[counterRecipes.size()];
        recipe = counterRecipes.toArray(recipe);
        return recipe;
    }

    public static void addFrierRecipe(ItemStack output, ItemStack ingredient)
    {
        frierRecipes.add(new RecipeFrier(ingredient, 100, output));
    }

    public static void addOreDicFrierRecipe(ItemStack output, String ingredient)
    {
        ArrayList<ItemStack> ingredientList = OreDictionary.getOres(ingredient);
        if (ingredientList != null && ingredientList.size() > 0)
        {
            ItemStack[] ingredients = ingredientList.toArray(new ItemStack[ingredientList.size()]);
            frierRecipes.add(new RecipeFrier(ingredients, 100, output));
        }
    }

    public static RecipeFrier[] getFrierRecipes()
    {
        RecipeFrier[] recipes = new RecipeFrier[frierRecipes.size()];
        recipes = frierRecipes.toArray(recipes);
        return recipes;
    }

    public static void addIceBoxRecipe(ItemStack output, ItemStack ingredient)
    {
        iceBoxRecipes.add(new RecipeIceBox(ingredient, -10, output));
    }

    public static void addOreDicIceBoxRecipe(ItemStack output, String ingredient)
    {
        ArrayList<ItemStack> ingredientList = OreDictionary.getOres(ingredient);
        if (ingredientList != null && ingredientList.size() > 0)
        {
            ItemStack[] ingredients = ingredientList.toArray(new ItemStack[ingredientList.size()]);
            iceBoxRecipes.add(new RecipeIceBox(ingredients, -10, output));
        }
    }

    public static RecipeIceBox[] getIceboxRecipes()
    {
        RecipeIceBox[] recipes = new RecipeIceBox[iceBoxRecipes.size()];
        recipes = iceBoxRecipes.toArray(recipes);
        return recipes;
    }

    public static void addOvenRecipe(ItemStack output, ItemStack ingredient)
    {
        ovenRecipes.add(new RecipeOven(ingredient, 250, output));
    }

    public static void addOreDicOvenRecipe(ItemStack output, String ingredient)
    {
        ArrayList<ItemStack> ingredientList = OreDictionary.getOres(ingredient);
        if (ingredientList != null && ingredientList.size() > 0)
        {
            ItemStack[] ingredients = ingredientList.toArray(new ItemStack[ingredientList.size()]);
            ovenRecipes.add(new RecipeOven(ingredients, 250, output));
        }
    }

    public static RecipeOven[] getOvenRecipes()
    {
        RecipeOven[] recipes = new RecipeOven[ovenRecipes.size()];
        recipes = ovenRecipes.toArray(recipes);
        return recipes;
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
        if (ingredents == null || ingredents.length <= 0) return null;

        ItemStack result = null;
        for (RecipeProcessor recipe : processorRecipes)
        {
            if (recipe.isInput(ingredents))
            {
                result = recipe.getOutput();
            }
        }
        return result;

    }

    public static RecipeProcessor[] getProcessorRecipes()
    {
        RecipeProcessor[] recipes = new RecipeProcessor[processorRecipes.size()];
        recipes = processorRecipes.toArray(recipes);
        return recipes;
    }

}
