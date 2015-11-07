package com.teammetallurgy.agriculture.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityBaseMachine extends TileEntity implements ISidedInventory
{
    protected byte facing = 0;
    ItemStack[] inventory;

    public TileEntityBaseMachine(int inventorySize)
    {
        inventory = new ItemStack[inventorySize];
    }

    @Override
    abstract public int getInventoryStackLimit();

    @Override
    abstract public String getInventoryName();

    public void setFacing(byte direction)
    {
        facing = direction;
    }

    public byte getFacing()
    {
        return facing;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        writeInventoryToNBT(compound);
        compound.setByte("facing", facing);

    }

    protected void writeInventoryToNBT(NBTTagCompound compound)
    {
        NBTTagList inventoryNBT = new NBTTagList();

        for (int i = 0; i < inventory.length; i++)
        {
            if (inventory[i] != null)
            {
                NBTTagCompound slotCompound = new NBTTagCompound();
                slotCompound.setByte("Slot", (byte) i);
                inventory[i].writeToNBT(slotCompound);

                inventoryNBT.appendTag(slotCompound);
            }
        }

        compound.setTag("Items", inventoryNBT);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        readInventoryFromNBT(compound);
        facing = compound.getByte("facing");

    }

    protected void readInventoryFromNBT(NBTTagCompound compound)
    {
        NBTTagList inventoryNBT = compound.getTagList("Items", 10);

        for (int i = 0; i < inventoryNBT.tagCount(); i++)
        {
            NBTTagCompound slotCompound = inventoryNBT.getCompoundTagAt(i);
            byte slotId = slotCompound.getByte("Slot");

            if (slotId >= 0 && slotId < inventory.length)
            {
                inventory[slotId] = ItemStack.loadItemStackFromNBT(slotCompound);
            }
        }
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        NBTTagCompound nbtCompound = pkt.func_148857_g();
        if (nbtCompound == null) return;

        readFromNBT(nbtCompound);
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbtCompound = new NBTTagCompound();

        writeToNBT(nbtCompound);

        S35PacketUpdateTileEntity packet = new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbtCompound);
        return packet;
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotId)
    {
        if (slotId >= 0 && slotId < inventory.length) { return inventory[slotId]; }

        return null;
    }

    @Override
    public ItemStack decrStackSize(int slotId, int amount)
    {
        if (inventory[slotId] == null) { return null; }

        ItemStack requestedStack = null;

        if (inventory[slotId].stackSize <= amount)
        {
            requestedStack = inventory[slotId];
            inventory[slotId] = null;
            return requestedStack;
        }

        requestedStack = inventory[slotId].splitStack(amount);

        if (inventory[slotId].stackSize == 0)
        {
            inventory[slotId] = null;
        }

        return requestedStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotId)
    {
        if (inventory[slotId] == null) { return null; }

        ItemStack slotStack = inventory[slotId];
        inventory[slotId] = null;
        return slotStack;
    }

    @Override
    public void setInventorySlotContents(int slotId, ItemStack itemStack)
    {
        inventory[slotId] = itemStack;

        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
        {
            inventory[slotId].stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return player.getDistanceSq(xCoord, yCoord, zCoord) <= 64;
    }

    @Override
    public void openInventory()
    {
        // not in use
    }

    @Override
    public void closeInventory()
    {
        // not in use
    }

    @Override
    abstract public boolean isItemValidForSlot(int slotId, ItemStack itemStack);

    @Override
    abstract public int[] getAccessibleSlotsFromSide(int side);

    @Override
    abstract public boolean canInsertItem(int slotId, ItemStack itemStack, int side);

    @Override
    abstract public boolean canExtractItem(int slotId, ItemStack itemStack, int side);

}
