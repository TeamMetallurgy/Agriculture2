package com.teammetallurgy.agriculture.machine.icebox;

import net.minecraft.item.ItemStack;

import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;

public class TileEntityIcebox extends TileEntityBaseMachine
{
    private static final int[] INPUT_SLOT = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13};
    private static final int [] OUTPUT_SLOT = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12};
    public TileEntityIcebox()
    {
        super(14, INPUT_SLOT, new int []{}, OUTPUT_SLOT);
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
    protected ItemStack getSmeltingResult(ItemStack... itemStack)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected int[] getInputSlots()
    {
        return INPUT_SLOT;
    }

    @Override
    protected int[] getOutputSlots()
    {
        return OUTPUT_SLOT;
    }

    @Override
    protected int getFuelSlot()
    {
        return -1;
    }

}
