package com.teammetallurgy.agriculture.machine.frier;

import net.minecraft.item.ItemStack;

import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;

public class TileEntityFrier extends TileEntityBaseMachine
{
    private static final int[] INPUT_SLOTS = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    private static final int FUEL_SLOT = 10;
    private static final int[] OUTPUT_SLOTS = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

    public TileEntityFrier()
    {
        super(11, INPUT_SLOTS, new int[] { FUEL_SLOT }, OUTPUT_SLOTS);
    }

    @Override
    public int getInventoryStackLimit()
    {
        // TODO Auto-generated method stub
        return 64;
    }

    @Override
    public String getInventoryName()
    {
        return "container.frier";
    }

    @Override
    protected ItemStack getSmeltingResult(ItemStack... itemStack)
    {
        return null;
    }

    @Override
    protected int[] getInputSlots()
    {
        return INPUT_SLOTS;
    }

    @Override
    protected int[] getOutputSlots()
    {
        return OUTPUT_SLOTS;
    }

    @Override
    protected int getFuelSlot()
    {
        return FUEL_SLOT;
    }

}
