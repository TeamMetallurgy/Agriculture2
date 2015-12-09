package com.teammetallurgy.agriculture.block.plant;

import java.util.Random;

import vazkii.botania.api.item.IHornHarvestable;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.teammetallurgy.agriculture.Agriculture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPlant extends BlockCrops implements IHornHarvestable
{
    protected ItemStack harvestItemStack;
    private IIcon plantIcons[];

    public BlockPlant()
    {
        this.setCreativeTab(Agriculture.instance.creativeTabBlock);
        float center = 0.35F;
        this.setBlockBounds(0.5F - center, 0.0F, 0.5F - center, 0.5F + center, 0.27F * 3.0F, 0.5F + center);
        this.setResistance(1.0F);
        this.setHardness(1.5F);
    }

    @Override
    public boolean onBlockActivated(World world, int xPos, int yPos, int zPos, EntityPlayer player, int side, float xFacing, float yFacing, float zFacing)
    {

        if (world.isRemote) return true;

        int meta = world.getBlockMetadata(xPos, yPos, zPos);

        if (meta > 0 && player.getHeldItem() == null)
        {
            EntityItem item = new EntityItem(world, xPos, yPos, zPos, harvestItemStack.copy());
            world.spawnEntityInWorld(item);
            world.setBlockMetadataWithNotify(xPos, yPos, zPos, meta - 1, 2);

            return true;
        }

        return false;
    }

    // Can be bonemealed
    @Override
    public boolean func_149851_a(World world, int xPos, int yPos, int zPos, boolean isRemote)
    {
        return world.getBlockMetadata(xPos, yPos, zPos) != 7;
    }

    // Apply bonemeal
    @Override
    public void func_149863_m(World world, int xPos, int yPos, int zPos)
    {
        int newMeta = world.getBlockMetadata(xPos, yPos, zPos) + 1;

        if (newMeta > 7)
        {
            newMeta = 7;
        }

        world.setBlockMetadataWithNotify(xPos, yPos, zPos, newMeta, 2);
    }

    @Override
    public int getRenderType()
    {
        return 1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.plantIcons = new IIcon[7];

        for (int i = 0; i < this.plantIcons.length; ++i)
        {
            this.plantIcons[i] = p_149651_1_.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        if (meta < 6)
        {
            return this.plantIcons[meta];
        }
        else
        {
            return this.plantIcons[6];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int xPos, int yPos, int zPos)
    {
        return Item.getItemFromBlock(this);
    }

    @Override
    public Item getItemDropped(int world, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }

    /* IHornHarvestable implementation */
    @Override
    public boolean canHornHarvest(World world, int x, int y, int z, ItemStack stack, EnumHornType hornType)
    {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta > 0 && hornType == EnumHornType.WILD) { return true; }
        return false;
    }

    @Override
    public boolean hasSpecialHornHarvest(World world, int x, int y, int z, ItemStack stack, EnumHornType hornType)
    {
        return true;
    }

    @Override
    public void harvestByHorn(World world, int x, int y, int z, ItemStack stack, EnumHornType hornType)
    {
        if (world.isRemote || hornType != EnumHornType.WILD) { return; }

        int meta = world.getBlockMetadata(x, y, z);

        if (meta > 0)
        {
            EntityItem item = new EntityItem(world, x, y, z, harvestItemStack.copy());
            world.spawnEntityInWorld(item);
            world.setBlockMetadataWithNotify(x, y, z, meta - 1, 2);
        }
    }
    /* IHornHarvestable implementation - end */

}
