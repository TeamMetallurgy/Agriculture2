package com.teammetallurgy.agriculture.machine.processor;

import net.minecraft.item.ItemStack;

import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;

public class TileEntityProcessor extends TileEntityBaseMachine
{
    private static final int[] INPUT_SLOT = new int[] {1,2};
    private static final int FUEL_SLOT = 0;
    private static final int[] OUTPUT_SLOT = new int[] {3}; 

    public TileEntityProcessor()
    {
        super(4, INPUT_SLOT, new int[]{FUEL_SLOT}, OUTPUT_SLOT);
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
    protected ItemStack getSmeltingResult(ItemStack... itemStack)
    { 
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
        return FUEL_SLOT;
    }

}
