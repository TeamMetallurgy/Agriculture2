package com.teammetallurgy.agriculture.machine.icebox;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.teammetallurgy.agriculture.Agriculture;
import com.teammetallurgy.metallurgycore.machines.BlockMetallurgyCore;

public class BlockIcebox extends BlockMetallurgyCore
{

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void doOnActivate(World world, int x, int y, int z, EntityPlayer player, int side, float xOffset, float yOffset, float zOffset)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return Agriculture.instance.creativeTabBlock;
    }

}