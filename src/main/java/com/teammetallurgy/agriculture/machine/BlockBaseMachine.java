package com.teammetallurgy.agriculture.machine;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.teammetallurgy.agriculture.Agriculture;

public abstract class BlockBaseMachine extends BlockContainer
{
    Random random = new Random();

    public BlockBaseMachine()
    {
        super(Material.rock);
        this.setBlockTextureName("minecraft:brick");
        this.setHardness(3F);
    }

    @Override
    abstract public TileEntity createNewTileEntity(World world, int meta);

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityBaseMachine)
        {
            TileEntityBaseMachine tileEntityMachine = (TileEntityBaseMachine) tileEntity;

            // Dropping inventory
            for (int i = 0; i < tileEntityMachine.getSizeInventory(); i++)
            {
                ItemStack itemstack = tileEntityMachine.getStackInSlot(i);

                if (itemstack != null)
                {
                    double offsetX = random.nextFloat() * 0.8D + 0.1D;
                    double offsetY = random.nextFloat() * 0.8D + 0.1D;
                    double offsetZ = random.nextFloat() * 0.8D + 0.1D;

                    while (itemstack.stackSize > 0)
                    {
                        int split = this.random.nextInt(21) + 10;

                        if (split > itemstack.stackSize)
                        {
                            split = itemstack.stackSize;
                        }

                        itemstack.stackSize -= split;
                        ItemStack spawningStack = itemstack.copy();
                        EntityItem entityitem = new EntityItem(world, x + offsetX, y + offsetY, z + offsetZ, spawningStack);

                        double motionOffset = 0.05D;
                        entityitem.motionX = (double) (random.nextGaussian() * motionOffset);
                        entityitem.motionY = (double) (random.nextGaussian() * motionOffset + 0.2D);
                        entityitem.motionZ = (double) (random.nextGaussian() * motionOffset);
                        world.spawnEntityInWorld(entityitem);
                    }
                }
            }
        }
        super.breakBlock(world, x, y, z, block, meta);
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
