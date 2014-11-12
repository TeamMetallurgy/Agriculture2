package com.teammetallurgy.agriculture.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.teammetallurgy.agriculture.machine.ContainerFuel;
import com.teammetallurgy.agriculture.machine.GuiFuel;
import com.teammetallurgy.agriculture.machine.TileEntityBaseMachine;
import com.teammetallurgy.agriculture.machine.brewer.ContainerBrewer;
import com.teammetallurgy.agriculture.machine.brewer.GuiBrewer;
import com.teammetallurgy.agriculture.machine.brewer.TileEntityBrewer;
import com.teammetallurgy.agriculture.machine.counter.ContainerCabinet;
import com.teammetallurgy.agriculture.machine.counter.ContainerCounter;
import com.teammetallurgy.agriculture.machine.counter.GuiCabinet;
import com.teammetallurgy.agriculture.machine.counter.GuiCounter;
import com.teammetallurgy.agriculture.machine.counter.TileEntityCounter;
import com.teammetallurgy.agriculture.machine.frier.ContainerFrier;
import com.teammetallurgy.agriculture.machine.frier.GuiFrier;
import com.teammetallurgy.agriculture.machine.frier.TileEntityFrier;
import com.teammetallurgy.agriculture.machine.icebox.ContainerIcebox;
import com.teammetallurgy.agriculture.machine.icebox.GuiIcebox;
import com.teammetallurgy.agriculture.machine.icebox.TileEntityIcebox;
import com.teammetallurgy.agriculture.machine.oven.ContainerOven;
import com.teammetallurgy.agriculture.machine.oven.GuiOven;
import com.teammetallurgy.agriculture.machine.oven.TileEntityOven;
import com.teammetallurgy.agriculture.machine.processor.ContainerProcessor;
import com.teammetallurgy.agriculture.machine.processor.GuiProcessor;
import com.teammetallurgy.agriculture.machine.processor.TileEntityProcessor;
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
            case GuiIds.BREWER:
                return new ContainerBrewer(player.inventory, (TileEntityBrewer) tileEntity);
            case GuiIds.CABINET:
                return new ContainerCabinet(player.inventory, (TileEntityCounter) tileEntity);
            case GuiIds.COUNTER:
                return new ContainerCounter(player.inventory, (TileEntityBaseMachine) tileEntity);
            case GuiIds.FRIER:
                return new ContainerFrier(player.inventory, (TileEntityFrier) tileEntity);
            case GuiIds.FUEL:
                return new ContainerFuel(player.inventory, (TileEntityBaseMachine) tileEntity);
            case GuiIds.ICEBOX:
                return new ContainerIcebox(player.inventory, (TileEntityIcebox) tileEntity);
            case GuiIds.OVEN:
                return new ContainerOven(player.inventory, (TileEntityOven) tileEntity);
            case GuiIds.PROCESSOR:
                return new ContainerProcessor(player.inventory, (TileEntityProcessor) tileEntity);
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
            case GuiIds.BREWER:
                return new GuiBrewer(new ContainerBrewer(player.inventory, (TileEntityBrewer) tileEntity));
            case GuiIds.CABINET:
                return new GuiCabinet(new ContainerCabinet(player.inventory, (TileEntityCounter) tileEntity));
            case GuiIds.COUNTER:
                return new GuiCounter(new ContainerCounter(player.inventory, (TileEntityBaseMachine) tileEntity));
            case GuiIds.FRIER:
                return new GuiFrier(new ContainerFrier(player.inventory, (TileEntityFrier) tileEntity));
            case GuiIds.FUEL:
                return new GuiFuel(new ContainerFuel(player.inventory, (TileEntityBaseMachine) tileEntity));
            case GuiIds.ICEBOX:
                return new GuiIcebox(new ContainerIcebox(player.inventory, (TileEntityIcebox) tileEntity));
            case GuiIds.OVEN:
                return new GuiOven(new ContainerOven(player.inventory, (TileEntityOven) tileEntity));
            case GuiIds.PROCESSOR:
                return new GuiProcessor(new ContainerProcessor(player.inventory, (TileEntityProcessor) tileEntity));
            default:
                return null;
        }
    }
}
