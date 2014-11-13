package com.teammetallurgy.agriculture.machine.counter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;

public class ContainerCounter extends Container
{
    private TileEntityBaseMachine machine;

    public ContainerCounter(InventoryPlayer invPlayer, TileEntityBaseMachine tileEntity)
    {
        this.machine = tileEntity;

        int i;

        // Player inventory

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
        }
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
