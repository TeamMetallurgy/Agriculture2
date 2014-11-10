package com.teammetallurgy.agriculture.machine.oven;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.teammetallurgy.agriculture.machine.BlockBaseMachine;

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
        // TODO Auto-generated method stub

    }

}
