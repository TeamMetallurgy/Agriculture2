package com.teammetallurgy.agriculture;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.google.common.io.Resources;
import com.teammetallurgy.agriculture.food.Food;
import com.teammetallurgy.agriculture.food.FoodItem;
import com.teammetallurgy.agriculture.food.FoodSet;

public class ItemList
{

    private static HashMap<String, FoodSet> setList = new HashMap<String, FoodSet>();

    public static void preInit()
    {
        ItemList.initFoodList();
    }

    public static void recalculateValues()
    {
        for (String set : ItemList.setList.keySet())
        {
            FoodSet foodSet = ItemList.setList.get(set);

            for (Food food : foodSet.getFoods())
            {
                calculateValuesFor(foodSet.getItemStack(food.getName()));
            }
        }
    }

    private static void calculateValuesFor(ItemStack itemStack)
    {
        if (itemStack == null) { return; }

        Item item = itemStack.getItem();
        if ((item != null) && (item instanceof FoodItem))
        {
            ((FoodItem) item).calculateValues(itemStack.getItemDamage());
        }
    }

    private static void initFoodList()
    {
        String[] sets = { "base" };

        for (String set : sets)
        {

            String path = "assets/agriculture/data/";

            URL resource = Resources.getResource(path + set + ".json");

            try
            {
                ItemList.injectMetalSet(set, resource.openStream());
            }
            catch (IOException ignored)
            {
            }
        }
    }

    private static void injectMetalSet(String name, InputStream stream)
    {
        FoodSet foodSet = new FoodSet(name);

        foodSet.load(stream);

        ItemList.setList.put(name, foodSet);
    }

    public static ItemStack getItemStack(String setName, String foodName)
    {
        FoodSet foodSet = setList.get(setName);
        if (foodSet == null) return null;

        return foodSet.getItemStack(foodName);
    }

    public static FoodSet getSet(String setName)
    {
        return setList.get(setName);
    }

    public static String[] getSetNames()
    {
        return setList.keySet().toArray(new String[setList.size()]);
    }
}
