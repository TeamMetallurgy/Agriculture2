package com.teammetallurgy.agriculture.machine.brewer;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;
import com.teammetallurgy.agriculture.utils.AgricultureDirection;

public class TileEntityBrewer extends TileEntityBaseMachine implements IFluidHandler
{

    private static final int[] INPUT_SLOT = { 1, 2 };
    private static final int FUEL_SLOT = 0;
    private static final int OUTPUT_SLOT = 2;

    private FluidTank leftTank = new FluidTank(8000);
    private FluidTank rightTank = new FluidTank(8000);
    private static final int MAX_FLUID_TRANSFER = 1000;

    public TileEntityBrewer()
    {
        super(3);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public String getInventoryName()
    {
        return "container.brewer";
    }

    @Override
    public boolean isItemValidForSlot(int slotId, ItemStack itemStack)
    {
        return true;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        switch (side)
        {
            case 0:
                return new int[] { OUTPUT_SLOT };
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
        return slotId == 2 && side == 0;
    }

    // Implementing IFluidHandler
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
    {
        if (resource == null) { return 0; }

        FluidStack fillingStack = resource.copy();
        if (fillingStack.amount > MAX_FLUID_TRANSFER)
        {
            fillingStack.amount = MAX_FLUID_TRANSFER;
        }

        int amount = leftTank.fill(resource, doFill);
        if (doFill)
        {
            markDirty();
        }
        return amount;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
    {
        if (resource == null || !resource.isFluidEqual(rightTank.getFluid())) { return null; }

        int withdrawAmount = resource.amount;
        if (withdrawAmount > MAX_FLUID_TRANSFER)
        {
            withdrawAmount = MAX_FLUID_TRANSFER;
        }

        if (doDrain)
        {
            markDirty();
        }

        return rightTank.drain(withdrawAmount, doDrain);
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

        return rightTank.drain(withdrawAmount, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid)
    {
        AgricultureDirection source = AgricultureDirection.convertFromForgeDirection(from);
        AgricultureDirection facingDirection = AgricultureDirection.getDirectionFromIndex(facing);

        if (facingDirection.getLeft() == source && (leftTank.getFluid() == null || leftTank.getFluid().getFluid() == fluid)) { return true; }
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
            return new FluidTankInfo[] { rightTank.getInfo() };
        }
        else if (facingDirection.getRight() == source) { return new FluidTankInfo[] { leftTank.getInfo() }; }
        return null;
    }
}
