package com.teammetallurgy.agriculture.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerFuel extends Container
{

    private TileEntityBaseMachine machine;

    public ContainerFuel(InventoryPlayer invPlayer, TileEntityBaseMachine tileEntity)
    {
        this.machine = tileEntity;
    }
    
    public TileEntityBaseMachine getMachine()
    {
        return this.machine;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

}
