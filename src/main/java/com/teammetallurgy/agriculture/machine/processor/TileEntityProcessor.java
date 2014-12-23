package com.teammetallurgy.agriculture.machine.processor;

import net.minecraft.item.ItemStack;

import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;
import com.teammetallurgy.agriculture.recpies.Recipes;

public class TileEntityProcessor extends TileEntityBaseMachine
{
    private static final int[] INPUT_SLOT = new int[] { 1, 2 };
    private static final int FUEL_SLOT = 0;
    private static final int[] OUTPUT_SLOT = new int[] { 3 };

    public TileEntityProcessor()
    {
        super(4, INPUT_SLOT, new int[] { FUEL_SLOT }, OUTPUT_SLOT);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public String getInventoryName()
    {
        return "container.processor";
    }

    @Override
    protected ItemStack getSmeltingResult(ItemStack... itemStack)
    {
        if (itemStack.length < 1) return null;
        if (itemStack[1] == null) return Recipes.getProcessorRecipeOutput(itemStack[0]);
        if (itemStack[0] == null) return Recipes.getProcessorRecipeOutput(itemStack[1]);

        return Recipes.getProcessorRecipeOutput(itemStack[0], itemStack[1]);
    }

    @Override
    protected int[] getInputSlots()
    {
        return INPUT_SLOT;
    }

    @Override
    protected int[] getOutputSlots()
    {
        return OUTPUT_SLOT;
    }

    @Override
    protected int getFuelSlot()
    {
        return FUEL_SLOT;
    }

    @Override
    protected boolean hasInput()
    {
        if ((getStackInSlot(1) == null) && (getStackInSlot(2) == null)) return false;
        return true;

    }

}
