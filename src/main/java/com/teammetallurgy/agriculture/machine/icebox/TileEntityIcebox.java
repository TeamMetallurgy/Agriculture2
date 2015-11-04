package com.teammetallurgy.agriculture.machine.icebox;

import net.minecraft.item.ItemStack;

import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;

public class TileEntityIcebox extends TileEntityBaseMachine
{
    private static final int[] INPUT_SLOT = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
    private static final int[] OUTPUT_SLOT = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

    public TileEntityIcebox()
    {
        super(14);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public String getInventoryName()
    {
        return "container.icebox";
    }

    @Override
    public boolean isItemValidForSlot(int slotId, ItemStack itemStack)
    {
        return true;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        if (side == 0) { return OUTPUT_SLOT; }

        return INPUT_SLOT;
    }

    @Override
    public boolean canInsertItem(int slotId, ItemStack itemStack, int side)
    {
        return isItemValidForSlot(slotId, itemStack);
    }

    @Override
    public boolean canExtractItem(int slotId, ItemStack itemStack, int side)
    {
        return side == 0 && slotId >= 1 && slotId <= 13;
    }
}
