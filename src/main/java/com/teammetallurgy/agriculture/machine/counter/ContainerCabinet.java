package com.teammetallurgy.agriculture.machine.counter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerCabinet extends Container
{
    private TileEntityCounter machine;

    public ContainerCabinet(InventoryPlayer invPlayer, TileEntityCounter teCounter)
    {
        this.machine = teCounter;

        int i;

        // Left storage
        for (i = 0; i < 3; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                this.addSlotToContainer(new Slot(this.machine, j + i * 3 + 20, 8 + j * 18, 14 + i * 18));
            }
        }

        // Right storage
        for (i = 0; i < 3; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                this.addSlotToContainer(new Slot(this.machine, j + i * 3 + 32, 98 + j * 18, 14 + i * 18));
            }
        }

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
