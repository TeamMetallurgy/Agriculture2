package com.teammetallurgy.agriculture.machine;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.teammetallurgy.agriculture.Agriculture;
import com.teammetallurgy.metallurgycore.machines.BlockMetallurgyCore;

public class BlockBaseMachine extends BlockMetallurgyCore
{

    public BlockBaseMachine()
    {
        this.setBlockTextureName("minecraft:brick");
        this.setHardness(3F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return null;
    }

    @Override
    protected void doOnActivate(World world, int x, int y, int z, EntityPlayer player, int side, float xOffset, float yOffset, float zOffset)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
    {
        super.onBlockPlacedBy(world, x, y, z, entity, itemStack);
        int entityFacing = 0;

        if (entity != null) entityFacing = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity == null || !(tileEntity instanceof TileEntityBaseMachine)) return;

        switch (entityFacing)
        {
            case 0:
                ((TileEntityBaseMachine) tileEntity).setFacing((byte) 2);
                break;
            case 1:
                ((TileEntityBaseMachine) tileEntity).setFacing((byte) 3);
                break;
            case 2:
                ((TileEntityBaseMachine) tileEntity).setFacing((byte) 0);
                break;
            case 3:
                ((TileEntityBaseMachine) tileEntity).setFacing((byte) 1);
                break;
            default:
                ((TileEntityBaseMachine) tileEntity).setFacing((byte) 0);
                break;
        }

    }

    @Override
    public CreativeTabs getCreativeTabToDisplayOn()
    {
        return Agriculture.instance.creativeTabBlock;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

}
