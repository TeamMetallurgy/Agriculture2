package com.teammetallurgy.agriculture.machine.oven;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;

public class TileEntityOven extends TileEntityBaseMachine
{
    private static final int[] INPUT_SLOTS = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19 };
    private static final int FUEL_SLOT = 0;
    private static final int[] OUTPUT_SLOTS = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };

    public TileEntityOven()
    {
        super(20);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public String getInventoryName()
    {
        return "container.oven";
    }

    @Override
    public boolean isItemValidForSlot(int slotId, ItemStack itemStack)
    {
        if (slotId == 0) { return TileEntityFurnace.isItemFuel(itemStack); }

        return true;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        switch (side)
        {
            case 0:
                return OUTPUT_SLOTS;
            case 1:
                return INPUT_SLOTS;
            default:
                return new int[] { FUEL_SLOT };
        }
    }

    @Override
    public boolean canInsertItem(int slotId, ItemStack itemStack, int side)
    {
        return isItemValidForSlot(slotId, itemStack);
    }

    @Override
    public boolean canExtractItem(int slotId, ItemStack itemStack, int side)
    {
        return side == 0 && slotId >= 1 && slotId <= 16;
    }

}
