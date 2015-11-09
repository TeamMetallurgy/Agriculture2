package com.teammetallurgy.agriculture.recpies;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeProcessor
{
    protected ItemStack[] ingredientStacks1;
    protected ItemStack[] ingredientStacks2;
    protected ItemStack outputStack;

    public RecipeProcessor(ItemStack ingredient, ItemStack output)
    {
        this.ingredientStacks1 = new ItemStack[] { ingredient };
        this.outputStack = output;
    }

    public RecipeProcessor(ItemStack[] ingredients, ItemStack output)
    {
        this.ingredientStacks1 = ingredients;
        this.outputStack = output;
    }

    public RecipeProcessor(ItemStack ingredient1, ItemStack ingredient2, ItemStack output)
    {
        this.ingredientStacks1 = new ItemStack[] { ingredient1 };
        this.ingredientStacks2 = new ItemStack[] { ingredient2 };
        this.outputStack = output;
    }

    public RecipeProcessor(ItemStack[] ingredients1, ItemStack[] ingredients2, ItemStack output)
    {
        this.ingredientStacks1 = ingredients1;
        this.ingredientStacks2 = ingredients2;
        this.outputStack = output;
    }

    public boolean isInput(ItemStack... inputs)
    {
        if (inputs.length == 1 && inputs[0] != null && ingredientStacks2 == null)
        {
            ItemStack input = inputs[0];
            for (ItemStack ingredient : ingredientStacks1)
            {
                if (OreDictionary.itemMatches(ingredient, input, false)) { return true; }
            }
        }
        else if (inputs.length == 2 && inputs[0] != null && inputs[1] == null)
        {
            ItemStack input = inputs[0];
            for (ItemStack ingredient : ingredientStacks1)
            {
                if (OreDictionary.itemMatches(ingredient, input, false)) { return true; }
            }
        }
        else if (inputs.length == 2 && inputs[0] != null && inputs[1] != null)
        {
            boolean matchedFirst = false;
            boolean matchedSecond = false;

            ItemStack input1 = inputs[0];
            ItemStack input2 = inputs[1];

            for (ItemStack ingredient : ingredientStacks1)
            {
                if (OreDictionary.itemMatches(ingredient, input1, false))
                {
                    matchedFirst = true;
                    break;
                }

                if (OreDictionary.itemMatches(ingredient, input2, false))
                {
                    matchedSecond = true;
                    break;
                }
            }

            ItemStack secondCheckStack = null;
            if (matchedFirst)
            {
                secondCheckStack = input2;
            }
            else if (matchedSecond)
            {
                secondCheckStack = input1;
            }

            if (secondCheckStack == null) { return false; }

            for (ItemStack ingredient : ingredientStacks2)
            {
                if (OreDictionary.itemMatches(ingredient, secondCheckStack, false)) { return true; }
            }
        }
        return false;
    }

    public ItemStack getOutput()
    {
        return outputStack.copy();
    }

    public ItemStack[] getInputs(int slot)
    {
        switch (slot)
        {
            case 0:
                return ingredientStacks1;
            case 1:
                if (ingredientStacks2 != null) return ingredientStacks2;
                else return null;
            default:
                return null;
        }
    }
}
