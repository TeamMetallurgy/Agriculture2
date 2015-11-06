package com.teammetallurgy.agriculture.food;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Set;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.google.gson.Gson;
import com.teammetallurgy.agriculture.Agriculture;
import com.teammetallurgy.agriculture.handler.AgricultureLogHandler;
import com.teammetallurgy.agriculture.recpies.Recipes;

import cpw.mods.fml.common.registry.GameRegistry;

public class FoodSet
{
    private final String name;
    private final String setTag;
    private Food[] foods;

    private final HashMap<String, ItemStack> itemStacks = new HashMap<String, ItemStack>();

    private FoodItem defaultItem;
    private final HashMap<String, String[]> recipes = new HashMap<String, String[]>();

    public FoodSet(String setName)
    {
        this.name = setName;
        this.setTag = this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
        this.initDefaults();
    }

    public String[] getRecipe(String food)
    {
        return this.recipes.get(food);
    }

    public Set<String> getRecipes()
    {
        return this.recipes.keySet();
    }

    private void initDefaults()
    {
        String postfix = this.name.toLowerCase();
        postfix = postfix.replace(" ", ".");

        this.defaultItem = new FoodItem(postfix, 2);
    }

    public void load(InputStream inputStream)
    {
        Reader reader = null;
        try
        {
            reader = new InputStreamReader(inputStream, "UTF-8");

        }
        catch (IOException e)
        {
            AgricultureLogHandler.warn(e.getLocalizedMessage());
        }

        this.foods = new Gson().fromJson(reader, Food[].class);

        int meta;
        for (meta = 0; meta < this.foods.length; meta++)
        {

            Food food = foods[meta];
            String texture = food.getName().replace(" ", "_");
            texture = Agriculture.MODID + ":" + this.name + "/" + texture.toLowerCase();

            String tag = food.getName().replace(" ", "");
            FoodItem item = null;

            String identifier = "crop";
            if (food.type == Food.FoodType.base)
            {
                item = this.createItem(this.defaultItem, meta, tag);
                item.addSubItem(meta, food.getName(), food.type, texture, food.method);
                ItemStack stack = new ItemStack(item, 1, meta);

                OreDictionary.registerOre(identifier + tag, stack);

                this.itemStacks.put(tag, stack);
            }

            identifier = "food";
            if (food.type == Food.FoodType.edible)
            {
                item = this.createItem(this.defaultItem, meta, tag);
                item.addSubItem(meta, food.getName(), food.type, texture, food.method);
                ItemStack stack = new ItemStack(item, 1, meta);

                OreDictionary.registerOre(identifier + tag, stack);

                this.itemStacks.put(tag, new ItemStack(item, 1, meta));
            }

            if (food.method == Food.Methods.process && food.recipe != null)
            {
                if (food.recipe.length == 1)
                {
                    String oreDicInput = "food" + food.recipe[0].replace(" ", "");
                    Recipes.addProcessorRecipeOreDic(oreDicInput, "", "food" + tag);
                }
                if (food.recipe.length == 2)
                {
                    String OreDicInput1 = "food" + food.recipe[0].replace(" ", "");
                    String OreDicInput2 = "food" + food.recipe[1].replace(" ", "");

                    Recipes.addProcessorRecipeOreDic(OreDicInput1, OreDicInput2, "food" + tag);
                }

            }

            if (food.recipe != null)
            {
                this.recipes.put(tag, food.recipe);
            }
        }
    }

    private FoodItem createItem(FoodItem foodItem, int meta, String tag)
    {
        if (meta == 0)
        {
            GameRegistry.registerItem(foodItem, this.name + ".item");
        }

        return foodItem;
    }

    public ItemStack getItemStack(String food)
    {
        return this.itemStacks.get(food);
    }

}
