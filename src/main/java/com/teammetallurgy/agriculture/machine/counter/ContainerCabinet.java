package com.teammetallurgy.agriculture.machine.counter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerCabinet extends Container
{
    private TileEntityCounter machine;

    public ContainerCabinet(InventoryPlayer invPlayer, TileEntityCounter teCounter)
    {
        this.machine = teCounter;

    }

    public TileEntityCounter getMachine()
    {
        return machine;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

}
