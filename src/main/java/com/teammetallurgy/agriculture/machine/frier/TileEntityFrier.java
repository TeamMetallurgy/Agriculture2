package com.teammetallurgy.agriculture.machine.frier;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;

public class TileEntityFrier extends TileEntityBaseMachine
{
    private static final int[] INPUT_SLOTS = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    private static final int FUEL_SLOT = 0;
    private static final int[] OUTPUT_SLOTS = new int[] { 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    public TileEntityFrier()
    {
        super(11);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public String getInventoryName()
    {
        return "container.frier";
    }

    @Override
    public boolean isItemValidForSlot(int slotId, ItemStack itemStack)
    {
        if (slotId == FUEL_SLOT) { return TileEntityFurnace.isItemFuel(itemStack); }

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
        return side == 0 && slotId >= 2 && slotId <= 10;
    }
}
