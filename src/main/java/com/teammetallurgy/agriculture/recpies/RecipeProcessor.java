package com.teammetallurgy.agriculture.recpies;

import net.minecraft.item.ItemStack;

public class RecipeProcessor
{
    protected ItemStack ingredientStack1;
    protected ItemStack ingredientStack2;
    protected ItemStack outputStack;

    public RecipeProcessor(ItemStack ingredient, ItemStack output)
    {
        this.ingredientStack1 = ingredient;
        this.outputStack = output;
    }

    public RecipeProcessor(ItemStack ingredient1, ItemStack ingredient2, ItemStack output)
    {
        // TODO: OreDic support
        this.ingredientStack1 = ingredient1;
        this.ingredientStack2 = ingredient2;
        this.outputStack = output;
    }

    public boolean isInput(ItemStack... itemStacks)
    {
        // TODO: Rewrite
        return false;
    }

    public ItemStack getOutput()
    {
        return outputStack.copy();
    }
    
    public ItemStack getInput(int slot)
    {
        switch (slot){
            case 0:
                return ingredientStack1.copy();
            case 1:
                if (ingredientStack2 != null)
                    return ingredientStack2.copy();
                else
                    return null;
           default:
               return null;
        }
    }
}
