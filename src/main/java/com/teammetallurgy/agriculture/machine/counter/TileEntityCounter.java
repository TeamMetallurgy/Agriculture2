package com.teammetallurgy.agriculture.machine.counter;

import net.minecraft.item.ItemStack;

import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;

public class TileEntityCounter extends TileEntityBaseMachine
{
    private static final int[] INPUT_SLOTS = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41,
            42, 43 };
    private static final int[] OUTPUT_SLOTS = new int[] { 16, 17, 18, 19 };

    public TileEntityCounter()
    {
        super(44);
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
    public boolean isItemValidForSlot(int slotId, ItemStack itemStack)
    {
        return true;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        if (side == 0) { return OUTPUT_SLOTS; }
        return INPUT_SLOTS;
    }

    @Override
    public boolean canInsertItem(int slotId, ItemStack itemStack, int side)
    {
        return isItemValidForSlot(slotId, itemStack);
    }

    @Override
    public boolean canExtractItem(int slotId, ItemStack itemStack, int side)
    {
        return side == 0 && slotId >= 16 && slotId <= 19;
    }

}
