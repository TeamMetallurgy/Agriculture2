package com.teammetallurgy.agriculture.machine;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.teammetallurgy.metallurgycore.machines.TileEntityMetallurgySided;

public class TileEntityBaseMachine extends TileEntityMetallurgySided
{
    protected byte facing = 0;

    public TileEntityBaseMachine(int numberOfItemStacks, int[] slotsTop, int[] slotsSide, int[] slotsBottom)
    {
        super(numberOfItemStacks, slotsTop, slotsSide, slotsBottom);
        // TODO Auto-generated constructor stub
    }

    @Override
    public int getInventoryStackLimit()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getInventoryName()
    {
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected int[] getOutputSlots()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected int getFuelSlot()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public void setFacing(byte direction)
    {
        facing = direction;
    }

    public byte getFacing()
    {
        return facing;
    }

    @Override
    protected void writeCustomNBT(NBTTagCompound compound)
    {
        super.writeCustomNBT(compound);
        compound.setByte("facing", facing);

    }

    @Override
    protected void readCustomNBT(NBTTagCompound compound)
    {
        super.readCustomNBT(compound);
        facing = compound.getByte("facing");

    }

}
