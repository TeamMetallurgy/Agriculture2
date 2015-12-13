package com.teammetallurgy.agriculture.machine.frier;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import com.teammetallurgy.agriculture.machine.IFueledMachine;
import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;
import com.teammetallurgy.agriculture.utils.AgricultureDirection;

public class TileEntityFrier extends TileEntityBaseMachine implements IFluidHandler, IFueledMachine
{
    private static final int[] INPUT_SLOTS = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    private static final int FUEL_SLOT = 0;
    private static final int[] OUTPUT_SLOTS = new int[] { 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    private FluidTank tank = new FluidTank(8000);
    private static int MAX_FLUID_TRANSFER = 1000;

    public int burningTicks = 0;
    public int maxBurningTicks;

    public TileEntityFrier()
    {
        super(11);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public String getInventoryName()
    {
        return "container.frier";
    }

    @Override
    public boolean isItemValidForSlot(int slotId, ItemStack itemStack)
    {
        if (slotId == FUEL_SLOT) { return TileEntityFurnace.isItemFuel(itemStack); }

        return true;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        switch (side)
        {
            case 0:
                return OUTPUT_SLOTS;
            case 1:
                return INPUT_SLOTS;
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
        return side == 0 && slotId >= 2 && slotId <= 10;
    }

    /* IFluidHandler Implementation */
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
    {
        if (resource == null) { return 0; }

        FluidStack fillingStack = resource.copy();
        if (fillingStack.amount > MAX_FLUID_TRANSFER)
        {
            fillingStack.amount = MAX_FLUID_TRANSFER;
        }

        int amount = tank.fill(resource, doFill);
        if (doFill)
        {
            markDirty();
        }
        return amount;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
    {
        if (resource == null || !resource.isFluidEqual(tank.getFluid())) { return null; }

        int withdrawAmount = resource.amount;
        if (withdrawAmount > MAX_FLUID_TRANSFER)
        {
            withdrawAmount = MAX_FLUID_TRANSFER;
        }

        if (doDrain)
        {
            markDirty();
        }

        return tank.drain(withdrawAmount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
        int withdrawAmount = maxDrain;
        if (withdrawAmount > MAX_FLUID_TRANSFER)
        {
            withdrawAmount = MAX_FLUID_TRANSFER;
        }

        if (doDrain)
        {
            markDirty();
        }

        return tank.drain(withdrawAmount, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid)
    {
        AgricultureDirection source = AgricultureDirection.convertFromForgeDirection(from);
        AgricultureDirection facingDirection = AgricultureDirection.getDirectionFromIndex(facing);

        if (facingDirection.getLeft() == source && (tank.getFluid() == null || tank.getFluid().getFluid() == fluid)) { return true; }
        return false;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid)
    {
        AgricultureDirection source = AgricultureDirection.convertFromForgeDirection(from);
        AgricultureDirection facingDirection = AgricultureDirection.getDirectionFromIndex(facing);

        if (facingDirection.getRight() == source) { return true; }
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from)
    {
        AgricultureDirection source = AgricultureDirection.convertFromForgeDirection(from);
        AgricultureDirection facingDirection = AgricultureDirection.getDirectionFromIndex(facing);

        if (facingDirection.getRight() == source)
        {
            return new FluidTankInfo[] { tank.getInfo() };
        }
        else if (facingDirection.getRight() == source) { return new FluidTankInfo[] { tank.getInfo() }; }
        return null;
    }

    /* IFluidHandler Implementation - end */

    /* IFueledMachine implementation */
    @Override
    public boolean isBurning()
    {
        return burningTicks > 0;
    }

    @Override
    public int getScaledBurningTicks(int scale)
    {
        int displayTick = burningTicks;

        if (displayTick > maxBurningTicks)
        {
            displayTick = maxBurningTicks;
        }

        return displayTick * scale / maxBurningTicks;
    }

    /* IFueledMachine implementation - end */
}
