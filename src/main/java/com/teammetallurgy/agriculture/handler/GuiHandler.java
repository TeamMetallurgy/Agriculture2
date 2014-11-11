package com.teammetallurgy.agriculture.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;
import com.teammetallurgy.agriculture.machine.counter.ContainerCabinet;
import com.teammetallurgy.agriculture.machine.counter.ContainerCounter;
import com.teammetallurgy.agriculture.machine.counter.GuiCabinet;
import com.teammetallurgy.agriculture.machine.counter.GuiCounter;
import com.teammetallurgy.agriculture.machine.counter.TileEntityCounter;
import com.teammetallurgy.agriculture.utils.GuiIds;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity == null || !((tileEntity instanceof TileEntityBaseMachine))) return null;

        switch (ID)
        {
            case GuiIds.CABINET:
                return new ContainerCabinet(player.inventory, (TileEntityCounter) tileEntity);
            case GuiIds.COUNTER:
                return new ContainerCounter(player.inventory, (TileEntityBaseMachine) tileEntity);
            default:
                return null;

        }

    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity == null || !((tileEntity instanceof TileEntityBaseMachine))) return null;

        switch (ID)
        {
            case GuiIds.CABINET:
                return new GuiCabinet(new ContainerCabinet(player.inventory, (TileEntityCounter) tileEntity));
            case GuiIds.COUNTER:
                return new GuiCounter(new ContainerCounter(player.inventory, (TileEntityBaseMachine) tileEntity));
            default:
                return null;
        }

    }
}
