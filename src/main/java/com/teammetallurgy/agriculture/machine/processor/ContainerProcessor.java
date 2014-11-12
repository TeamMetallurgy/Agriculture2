package com.teammetallurgy.agriculture.machine.processor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerProcessor extends Container
{

    private TileEntityProcessor machine;

    public ContainerProcessor(InventoryPlayer invPlayer, TileEntityProcessor processor)
    {
        this.machine = processor;
    }

    public TileEntityProcessor getMachine()
    {
        return this.machine;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

}
