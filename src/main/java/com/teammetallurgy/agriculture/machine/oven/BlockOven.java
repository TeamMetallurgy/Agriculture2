package com.teammetallurgy.agriculture.machine.oven;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.teammetallurgy.agriculture.Agriculture;
import com.teammetallurgy.agriculture.machine.BlockBaseMachine;
import com.teammetallurgy.agriculture.utils.GuiIds;

public class BlockOven extends BlockBaseMachine
{
    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityOven();
    }

    @Override
    protected void doOnActivate(World world, int x, int y, int z, EntityPlayer player, int side, float xOffset, float yOffset, float zOffset)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (!(tileEntity instanceof TileEntityOven)) return;

        player.openGui(Agriculture.instance, GuiIds.OVEN, world, x, y, z);

    }

}
