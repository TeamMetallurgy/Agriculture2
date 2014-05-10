package com.teammetallurgy.agriculture;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.io.Resources;
import com.teammetallurgy.agriculture.food.FoodItem;
import com.teammetallurgy.agriculture.food.FoodSet;

public class ItemList
{

    private static HashMap<String, FoodSet> setList = new HashMap<String, FoodSet>();

    public static void preInit()
    {
        ItemList.initFoodList();
        OreDictionary.registerOre("cropWheat", new ItemStack(Items.wheat));
    }

    public static void recalculateValues()
    {
        for (String set : ItemList.setList.keySet())
        {
            FoodSet foodSet = ItemList.setList.get(set);

            for (String food : foodSet.getRecipes())
            {
                calculateValuesFor(foodSet.getItemStack(food));
            }
        }
    }

    private static void calculateValuesFor(ItemStack itemStack)
    {
        if (itemStack == null)
        {
            return;
        }

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

    public static void initRecipes()
    {
        for (String set : ItemList.setList.keySet())
        {
            FoodSet foodSet = ItemList.setList.get(set);

            for (String food : foodSet.getRecipes())
            {
                String[] recipes = foodSet.getRecipe(food);

                ArrayList<ItemStack> itemStacks = ItemList.getItemsFrom(recipes);

                ItemStack itemStack = foodSet.getItemStack(food);

                Item item = itemStack.getItem();
                if ((item != null) && (item instanceof FoodItem))
                {
                    ((FoodItem) item).updateRecipe(itemStack.getItemDamage(), itemStacks);
                }
            }
        }
    }

    private static ArrayList<ItemStack> getItemsFrom(String[] recipes)
    {
        ArrayList<ItemStack> itemStacks = new ArrayList<ItemStack>();
        for (String recipe : recipes)
        {
            ArrayList<ItemStack> ores = OreDictionary.getOres("crop" + recipe);
            if (!ores.isEmpty())
            {
                itemStacks.add(ores.get(0).copy());
            }
        }
        return itemStacks;
    }
}
