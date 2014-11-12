package com.teammetallurgy.agriculture.machine.oven;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerOven extends Container
{
    private TileEntityOven machine;
    
    public ContainerOven(InventoryPlayer invPlayer, TileEntityOven oven)
    {
        this.machine = oven;
    }
    
    public TileEntityOven getMachine()
    {
        return this.machine;
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_)
    {
        return true;
    }

}
