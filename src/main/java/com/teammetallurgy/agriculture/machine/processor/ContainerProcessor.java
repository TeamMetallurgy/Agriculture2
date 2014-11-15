package com.teammetallurgy.agriculture.machine.processor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerProcessor extends Container
{

    private TileEntityProcessor machine;

    public ContainerProcessor(InventoryPlayer invPlayer, TileEntityProcessor processor)
    {
        this.machine = processor;

        // Processor inventory
        this.addSlotToContainer(new Slot(this.machine, 1, 40, 32));
        this.addSlotToContainer(new Slot(this.machine, 2, 76, 32));
        this.addSlotToContainer(new Slot(this.machine, 3, 122, 32));

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
