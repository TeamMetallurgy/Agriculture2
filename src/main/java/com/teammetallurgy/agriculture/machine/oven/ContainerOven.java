package com.teammetallurgy.agriculture.machine.oven;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerOven extends Container
{
    private TileEntityOven machine;

    public ContainerOven(InventoryPlayer invPlayer, TileEntityOven oven)
    {
        this.machine = oven;

        int i;
        // Fuel slot
        this.addSlotToContainer(new Slot(this.machine, 0, 18, 34));

        // Cooking slots
        for (i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                this.addSlotToContainer(new Slot(this.machine, j + i * 4 + 1, 64 + j * 19, 6 + i * 19));
            }
        }
        
        // Tray slots
        for (i=0; i < 3; i++)
        {
            this.addSlotToContainer(new Slot(this.machine, i + 17, 142,25 + i * 19));
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
