package com.teammetallurgy.agriculture.food;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.google.gson.Gson;
import com.teammetallurgy.agriculture.Agriculture;
import com.teammetallurgy.metallurgycore.handlers.LogHandler;

import cpw.mods.fml.common.registry.GameRegistry;

public class FoodSet
{
    private final String name;
    private final String setTag;
    private Food[] foods;

    private final HashMap<String, ItemStack> itemStacks = new HashMap<String, ItemStack>();
    private int meta;

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
            LogHandler.log(e.getLocalizedMessage());
        }

        this.foods = new Gson().fromJson(reader, Food[].class);

        this.meta = 0;
        for (Food food : this.foods)
        {

            String texture = food.getName().replace(" ", "_");
            texture = Agriculture.MODID + ":" + this.name + "/" + texture.toLowerCase();

            String tag = food.getName().replace(" ", "");
            FoodItem item = null;

            String identifier = "crop";
            if (food.type == Food.FoodType.base)
            {
                item = this.createItem(this.defaultItem, this.meta++, tag, identifier);
                item.addSubItem(this.meta, food.getName(), 0, texture, food.method);
                this.itemStacks.put(tag, new ItemStack(item, 1, this.meta));
            }

            identifier = "food";
            if (food.type == Food.FoodType.edible)
            {
                item = this.createItem(this.defaultItem, this.meta++, tag, identifier);
                item.addSubItem(this.meta, food.getName(), 1, texture, food.method);
                this.itemStacks.put(tag, new ItemStack(item, 1, this.meta));
            }

            if (food.recipe != null)
            {
                this.recipes.put(tag, food.recipe);
            }
        }
    }

    private FoodItem createItem(FoodItem foodItem, int meta, String tag, String identifier)
    {
        if(meta == 0)
        {
            GameRegistry.registerItem(foodItem, this.name + "." + identifier);
        }

        OreDictionary.registerOre(identifier + tag, new ItemStack(foodItem, 1, meta));

        return foodItem;
    }

    public ItemStack getItemStack(String food)
    {
        return this.itemStacks.get(food);
    }
}
