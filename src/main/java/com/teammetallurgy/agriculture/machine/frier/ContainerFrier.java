package com.teammetallurgy.agriculture.machine.frier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerFrier extends Container
{
    private TileEntityFrier machine;

    public ContainerFrier(InventoryPlayer invPlayer, TileEntityFrier frier)
    {
        this.machine = frier;
    }

    public TileEntityFrier getMachine()
    {
        return this.machine;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

}
