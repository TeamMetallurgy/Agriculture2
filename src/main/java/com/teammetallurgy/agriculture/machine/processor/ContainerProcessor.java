package com.teammetallurgy.agriculture.machine.processor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerProcessor extends Container
{

    private TileEntityProcessor machine;
    private int lastProcessingTicks;
    private int lastBurningTicks;
    private int lastMaxBurningTicks;

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
        return this.machine.isUseableByPlayer(player);
    }

    public void addCraftingToCrafters(ICrafting crafter)
    {
        super.addCraftingToCrafters(crafter);
        crafter.sendProgressBarUpdate(this, 0, this.machine.processingTicks);
        crafter.sendProgressBarUpdate(this, 1, this.machine.burningTicks);
        crafter.sendProgressBarUpdate(this, 2, this.machine.maxBurningTicks);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.lastProcessingTicks != this.machine.processingTicks)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.machine.processingTicks);
            }

            if (this.lastBurningTicks != this.machine.burningTicks)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.machine.burningTicks);
            }

            if (this.lastMaxBurningTicks != this.machine.maxBurningTicks)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.machine.maxBurningTicks);
            }
        }

        this.lastProcessingTicks = this.machine.processingTicks;
        this.lastBurningTicks = this.machine.burningTicks;
        this.lastMaxBurningTicks = this.machine.maxBurningTicks;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int propertyId, int value)
    {
        switch (propertyId)
        {
            case 0:
                this.machine.processingTicks = value;
                break;
            case 1:
                this.machine.burningTicks = value;
                break;
            case 2:
                this.machine.maxBurningTicks = value;
                break;
        }
    }

    // Shift clicking
    public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
    {
        ItemStack itemstack = null;
        // TODO: shiftClicking

        return itemstack;
    }

}
