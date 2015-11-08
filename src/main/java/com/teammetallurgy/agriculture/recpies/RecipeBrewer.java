package com.teammetallurgy.agriculture.recpies;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class RecipeBrewer
{
    private ItemStack[] ingredients;
    private FluidStack baseFluid;
    private FluidStack outputFluid;

    public RecipeBrewer(ItemStack ingredient, FluidStack base, FluidStack output)
    {
        this.ingredients = new ItemStack[] { ingredient };
        this.baseFluid = base;
        this.outputFluid = output;
    }

    public RecipeBrewer(ItemStack[] ingredients, FluidStack base, FluidStack output)
    {
        this.ingredients = ingredients;
        this.baseFluid = base;
        this.outputFluid = output;
    }

    public ItemStack[] getIngredients()
    {
        return ingredients.clone();
    }

    public FluidStack getBase()
    {
        return baseFluid.copy();
    }

    public FluidStack getOutput()
    {
        return outputFluid.copy();

    }
}
