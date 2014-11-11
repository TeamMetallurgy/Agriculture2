package com.teammetallurgy.agriculture.machine.counter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;

public class ContainerCounter extends Container
{
    private TileEntityBaseMachine machine;

    public ContainerCounter(InventoryPlayer inventoryPlayer, TileEntityBaseMachine tileEntity)
    {
        this.machine = tileEntity;
    }

    public TileEntityBaseMachine getMachine()
    {
        return machine;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

}
