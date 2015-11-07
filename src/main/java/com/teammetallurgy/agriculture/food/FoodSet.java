package com.teammetallurgy.agriculture.food;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.google.gson.Gson;
import com.teammetallurgy.agriculture.Agriculture;
import com.teammetallurgy.agriculture.food.Food.FoodType;
import com.teammetallurgy.agriculture.handler.AgricultureLogHandler;

import cpw.mods.fml.common.registry.GameRegistry;

public class FoodSet
{
    private final String name;
    private final String setTag;
    private Food[] foods;

    private HashMap<String, ItemStack> itemStacks = new HashMap<String, ItemStack>();

    private FoodItem defaultItem;

    public FoodSet(String setName)
    {
        this.name = setName;
        this.setTag = this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
        this.initDefaults();
    }

    private void initDefaults()
    {
        String postfix = this.name.toLowerCase();
        postfix = postfix.replace(" ", ".");

        this.defaultItem = new FoodItem(postfix, 2);
        GameRegistry.registerItem(defaultItem, this.name + ".item");
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

            String oreDicPrefix = "crop";

            if (food.type == FoodType.base || food.type == FoodType.edible)
            {
                oreDicPrefix = "food";
            }

            defaultItem.addSubItem(meta, food.getName(), food.type, texture, food.method);
            ItemStack stack = new ItemStack(defaultItem, 1, meta);

            OreDictionary.registerOre(oreDicPrefix + tag, stack);

            this.itemStacks.put(tag, stack);
        }
    }

    public ItemStack getItemStack(String food)
    {
        return this.itemStacks.get(food);
    }

    public Food[] getFoods()
    {
        return this.foods;
    }

    public Food getFoodInfo(String name)
    {
        Food food = null;
        for (int i = 0; i < foods.length; i++)
        {
            if (name.equals(foods[i].getName()))
            {
                food = foods[i];
                break;
            }
        }
        return food;
    }

}
