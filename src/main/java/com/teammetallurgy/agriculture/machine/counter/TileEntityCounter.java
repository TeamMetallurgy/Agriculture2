package com.teammetallurgy.agriculture.machine.counter;

import net.minecraft.item.ItemStack;

import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;

public class TileEntityCounter extends TileEntityBaseMachine
{
    private static final int[] INPUT_SLOTS = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
    private static final int[] OUTPUT_SLOTS = new int[] { 16, 17, 18, 19 };

    public TileEntityCounter()
    {
        super(20, INPUT_SLOTS, new int[] {}, OUTPUT_SLOTS);
    }

    @Override
    protected int getFuelSlot()
    {
        return -1;
    }

    @Override
    protected int[] getInputSlots()
    {
        return INPUT_SLOTS;
    }

    @Override
    public String getInventoryName()
    {
        return "container.counter";
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    protected int[] getOutputSlots()
    {
        return OUTPUT_SLOTS;
    }

    @Override
    protected ItemStack getSmeltingResult(ItemStack... arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
