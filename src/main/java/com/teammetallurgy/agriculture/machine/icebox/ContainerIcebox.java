package com.teammetallurgy.agriculture.machine.icebox;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerIcebox extends Container
{

    private TileEntityIcebox machine;

    public ContainerIcebox(InventoryPlayer invPlayer, TileEntityIcebox icebox)
    {
        this.machine = icebox;
    }

    public TileEntityIcebox getMachine()
    {
        return this.machine;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return true;
    }

}
