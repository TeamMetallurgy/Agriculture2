package com.teammetallurgy.agriculture.machine.icebox;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerIcebox extends Container
{

    private TileEntityIcebox machine;

    public ContainerIcebox(InventoryPlayer invPlayer, TileEntityIcebox icebox)
    {
        this.machine = icebox;

        int i;
        
        // Ice slot
        this.addSlotToContainer(new Slot(this.machine,0, 32,33));
        
        // input/output slots
        
        for (i=0; i<3 ; i++)
        {
            for (int j =0 ; j < 4; j++)
            {
                this.addSlotToContainer(new Slot(this.machine, j + i * 4 + 1, 74 + j * 18, 15 + i * 18));
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
