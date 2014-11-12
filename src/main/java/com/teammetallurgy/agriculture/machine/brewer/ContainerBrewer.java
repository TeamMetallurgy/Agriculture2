package com.teammetallurgy.agriculture.machine.brewer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerBrewer extends Container
{
    private TileEntityBrewer machine;

    public ContainerBrewer(InventoryPlayer invPlayer, TileEntityBrewer brewer)
    {
        this.machine = brewer;
    }

    public TileEntityBrewer getMachine()
    {
        return machine;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

}
