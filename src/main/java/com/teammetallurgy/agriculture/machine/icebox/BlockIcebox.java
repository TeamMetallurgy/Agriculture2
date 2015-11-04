package com.teammetallurgy.agriculture.machine.icebox;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.teammetallurgy.agriculture.Agriculture;
import com.teammetallurgy.agriculture.machine.BlockBaseMachine;
import com.teammetallurgy.agriculture.utils.GuiIds;

public class BlockIcebox extends BlockBaseMachine
{

    public BlockIcebox()
    {
        this.setBlockTextureName("minecraft:brick");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityIcebox();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float distanceX, float distanceY, float distanceZ)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity instanceof TileEntityIcebox)
        {

            player.openGui(Agriculture.instance, GuiIds.ICEBOX, world, x, y, z);
            return true;
        }
        return super.onBlockActivated(world, x, y, z, player, side, distanceX, distanceY, distanceZ);
    }

}
