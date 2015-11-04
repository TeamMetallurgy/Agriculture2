package com.teammetallurgy.agriculture.machine.brewer;

import net.minecraft.item.ItemStack;

import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;

public class TileEntityBrewer extends TileEntityBaseMachine
{

    private static final int[] INPUT_SLOT = { 1, 2 };
    private static final int FUEL_SLOT = 0;
    private static final int OUTPUT_SLOT = 2;

    public TileEntityBrewer()
    {
        super(3);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public String getInventoryName()
    {
        return "container.brewer";
    }

    @Override
    public boolean isItemValidForSlot(int slotId, ItemStack itemStack)
    {
        return true;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        switch (side)
        {
            case 0:
                return new int[] { OUTPUT_SLOT };
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
        return slotId == 2 && side == 0;
    }
}
