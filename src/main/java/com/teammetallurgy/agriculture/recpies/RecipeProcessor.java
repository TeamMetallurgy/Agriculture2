package com.teammetallurgy.agriculture.recpies;

import net.minecraft.item.ItemStack;

public class RecipeProcessor extends RecipeBase implements IAgricultureRecipe
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
        this.ingredientStack1 = ingredient1;
        this.ingredientStack2 = ingredient2;
        this.outputStack = output;
    }

    @Override
    public boolean isInputMatch(ItemStack... itemStacks)
    {
        if (itemStacks == null || itemStacks.length < 1) return false;

        switch (itemStacks.length)
        {
            case 1:
                if (ingredientStack1.isItemEqual(itemStacks[0])) return true;

                if (isOreDicMatch(ingredientStack1, itemStacks[0])) return true;

                return false;
            case 2:
                if (ingredientStack2 == null) return false;

                if (ingredientStack1.isItemEqual(itemStacks[0]) && ingredientStack2.isItemEqual(itemStacks[1])) return true;
                if (ingredientStack1.isItemEqual(itemStacks[1]) && ingredientStack2.isItemEqual(itemStacks[0])) return true;

                if (isOreDicMatch(ingredientStack1, itemStacks[0]) && isOreDicMatch(ingredientStack2, itemStacks[1])) return true;
                if (isOreDicMatch(ingredientStack1, itemStacks[1]) && isOreDicMatch(ingredientStack2, itemStacks[0])) return true;

                return false;

            default:
                return false;
        }

    }

    @Override
    public ItemStack getOutput()
    {
        return outputStack.copy();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof RecipeProcessor)) return false;

        RecipeProcessor otherRecipe = (RecipeProcessor) obj;
        if (this.ingredientStack1.equals(otherRecipe.ingredientStack1) && this.ingredientStack2.equals(otherRecipe.ingredientStack2) && this.outputStack.equals(otherRecipe.outputStack)) return true;

        return false;
    }

    @Override
    public int hashCode()
    {
        return this.ingredientStack1.getUnlocalizedName().hashCode() + this.ingredientStack2.getUnlocalizedName().hashCode() + this.outputStack.getUnlocalizedName().hashCode();
    }
}
