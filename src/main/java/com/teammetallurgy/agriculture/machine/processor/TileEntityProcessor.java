package com.teammetallurgy.agriculture.machine.processor;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;
import com.teammetallurgy.agriculture.recpies.Recipes;

public class TileEntityProcessor extends TileEntityBaseMachine
{
    private static final int[] INPUT_SLOT = new int[] { 1, 2 };
    private static final int FUEL_SLOT = 0;
    private static final int[] OUTPUT_SLOT = new int[] { 3 };

    public int burningTicks = 0;
    public int maxBurningTicks;
    public int processingTicks;
    public static final int MAX_PROCESSONG_TICKS = 200;

    public TileEntityProcessor()
    {
        super(4);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public String getInventoryName()
    {
        return "container.processor";
    }

    @Override
    public boolean isItemValidForSlot(int slotId, ItemStack itemStack)
    {
        if (slotId == 0) { return TileEntityFurnace.isItemFuel(itemStack); }

        return true;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        switch (side)
        {
            case 0:
                return OUTPUT_SLOT;
            case 1:
                return INPUT_SLOT;
            default:
                return new int[] { FUEL_SLOT };
        }
    }

    @Override
    public boolean canInsertItem(int slotId, ItemStack itemStack, int side)
    {
        return isItemValidForSlot(slotId, itemStack);
    }

    @Override
    public boolean canExtractItem(int slotId, ItemStack itemStack, int side)
    {
        return side == 1 && slotId == 3;
    }

    public int getScaledBurningTicks(int scale)
    {
        int displayTick = burningTicks;

        if (displayTick > maxBurningTicks)
        {
            displayTick = maxBurningTicks;
        }

        return displayTick * scale / maxBurningTicks;
    }

    public int getScaledProcessingTime(int scale)
    {
        int displayTick = processingTicks;

        if (displayTick > MAX_PROCESSONG_TICKS)
        {
            displayTick = MAX_PROCESSONG_TICKS;
        }

        return displayTick * scale / MAX_PROCESSONG_TICKS;
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        boolean burning = burningTicks > 0;
        boolean requiresUpdate = false;

        if (burning)
        {
            burningTicks--;
        }

        if (worldObj.isRemote) return;

        if (burningTicks != 0 || inventory[FUEL_SLOT] != null && (inventory[INPUT_SLOT[0]] != null))
        {
            if (burningTicks == 0 && canProcess())
            {
                // Try to start burning

                burningTicks = maxBurningTicks = TileEntityFurnace.getItemBurnTime(inventory[FUEL_SLOT]);

                if (burningTicks > 0)
                {
                    // Consume Fuel
                    requiresUpdate = true;

                    if (inventory[FUEL_SLOT] != null)
                    {
                        inventory[FUEL_SLOT].stackSize--;

                        // Replace with container if available, example: lava
                        // bucket -> empty bucket
                        if (inventory[FUEL_SLOT].stackSize == 0)
                        {
                            inventory[FUEL_SLOT] = inventory[FUEL_SLOT].getItem().getContainerItem(inventory[FUEL_SLOT]);
                        }
                    }
                }
            }

            if (burningTicks > 0 && canProcess())
            {
                processingTicks++;

                // Process Input
                if (processingTicks >= MAX_PROCESSONG_TICKS)
                {
                    processingTicks = 0;
                    processInput();
                    requiresUpdate = true;
                }
            }
            else
            {
                processingTicks = 0;
            }
        }

        if (burning != burningTicks > 0)
        {
            // visual updates
        }

        if (requiresUpdate) markDirty();

    }

    private boolean canProcess()
    {
        if (inventory[INPUT_SLOT[0]] == null && inventory[INPUT_SLOT[1]] == null) return false;

        ItemStack result = Recipes.getProcessorRecipeOutput(inventory[INPUT_SLOT[0]], inventory[INPUT_SLOT[1]]);

        if (result == null) return false;
        if (inventory[OUTPUT_SLOT[0]] == null) return true;
        if (!inventory[OUTPUT_SLOT[0]].isItemEqual(result)) return false;
        int totalStackSize = inventory[OUTPUT_SLOT[0]].stackSize + result.stackSize;
        return totalStackSize <= getInventoryStackLimit() && totalStackSize <= result.getMaxStackSize();
    }

    private void processInput()
    {
        if (!canProcess()) return;

        ItemStack result = Recipes.getProcessorRecipeOutput(inventory[INPUT_SLOT[0]], inventory[INPUT_SLOT[1]]);

        if (inventory[OUTPUT_SLOT[0]] == null)
        {
            inventory[OUTPUT_SLOT[0]] = result.copy();
        }
        else if (inventory[OUTPUT_SLOT[0]].isItemEqual(result))
        {
            inventory[OUTPUT_SLOT[0]].stackSize += result.stackSize;
        }

        if (inventory[INPUT_SLOT[0]] != null)
        {
            inventory[INPUT_SLOT[0]].stackSize--;

            if (inventory[INPUT_SLOT[0]].stackSize <= 0)
            {
                inventory[INPUT_SLOT[0]] = inventory[INPUT_SLOT[0]].getItem().getContainerItem(inventory[INPUT_SLOT[0]]);
            }
        }

        if (inventory[INPUT_SLOT[1]] != null)
        {
            inventory[INPUT_SLOT[1]].stackSize--;

            if (inventory[INPUT_SLOT[1]].stackSize <= 0)
            {
                inventory[INPUT_SLOT[1]] = inventory[INPUT_SLOT[1]].getItem().getContainerItem(inventory[INPUT_SLOT[1]]);
            }
        }
    }
}
