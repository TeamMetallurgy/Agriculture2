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
        super(3, INPUT_SLOT, new int[] { FUEL_SLOT }, new int[] { OUTPUT_SLOT });
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
    protected ItemStack getSmeltingResult(ItemStack... itemStack)
    {

        return null;
    }

    @Override
    protected int[] getInputSlots()
    {
        return TileEntityBrewer.INPUT_SLOT;
    }

    @Override
    protected int[] getOutputSlots()
    {
        return new int[] { TileEntityBrewer.OUTPUT_SLOT };
    }

    @Override
    protected int getFuelSlot()
    {
        return TileEntityBrewer.FUEL_SLOT;
    }

}
