package com.teammetallurgy.agriculture.machine.oven;

import net.minecraft.item.ItemStack;

import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;

public class TileEntityOven extends TileEntityBaseMachine
{
    private static final int[] INPUT_SLOTS = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
    private static final int FUEL_SLOT = 0;
    private static final int[] OUTPUT_SLOTS = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
    public TileEntityOven()
    {
        super(20, INPUT_SLOTS, new int[]{FUEL_SLOT}, OUTPUT_SLOTS);
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
    protected ItemStack getSmeltingResult(ItemStack... itemStack)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected int[] getInputSlots()
    {
        return INPUT_SLOTS;
    }

    @Override
    protected int[] getOutputSlots()
    {
        return OUTPUT_SLOTS;
    }

    @Override
    protected int getFuelSlot()
    { 
        return FUEL_SLOT;
    }
    

}
