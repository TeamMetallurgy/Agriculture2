package com.teammetallurgy.agriculture.machine.brewer;

import net.minecraft.item.ItemStack;

import com.teammetallurgy.metallurgycore.machines.TileEntityMetallurgySided;

public class TileEntityBrewer extends TileEntityMetallurgySided
{

    private static final int INPUT_SLOT = 0;
    private static final int FUEL_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    public TileEntityBrewer()
    {
        super(3, new int[] { INPUT_SLOT }, new int[] { FUEL_SLOT }, new int[] { OUTPUT_SLOT });
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
        return new int[] { TileEntityBrewer.INPUT_SLOT };
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
