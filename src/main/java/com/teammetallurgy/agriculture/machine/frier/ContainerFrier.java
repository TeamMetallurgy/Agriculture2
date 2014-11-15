package com.teammetallurgy.agriculture.machine.frier;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerFrier extends Container
{
    private TileEntityFrier machine;

    public ContainerFrier(InventoryPlayer invPlayer, TileEntityFrier frier)
    {
        this.machine = frier;

        int i;

        // Fluid slot
        this.addSlotToContainer(new Slot(this.machine, 1, 40, 35));

        // input/output slots

        for (i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                this.addSlotToContainer(new Slot(this.machine, j + i * 3 + 2, 89 + j * 18, 17 + i * 18));
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
