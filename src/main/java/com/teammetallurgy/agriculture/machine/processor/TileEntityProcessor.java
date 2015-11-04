package com.teammetallurgy.agriculture.machine.processor;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;

public class TileEntityProcessor extends TileEntityBaseMachine
{
    private static final int[] INPUT_SLOT = new int[] { 1, 2 };
    private static final int FUEL_SLOT = 0;
    private static final int[] OUTPUT_SLOT = new int[] { 3 };

    public TileEntityProcessor()
    {
        super(4);
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
                return OUTPUT_SLOT;
            case 1:
                return INPUT_SLOT;
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
        return side == 1 && slotId == 3;
    }
}
