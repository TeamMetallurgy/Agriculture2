package com.teammetallurgy.agriculture.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.teammetallurgy.agriculture.Agriculture;
import com.teammetallurgy.agriculture.ItemList;

public class BlockSalt extends Block
{
    private Item saltItem;
    private int saltMeta;

    public BlockSalt()
    {
        super(Material.rock);
        this.setHardness(1.5F);
        this.setBlockName(Agriculture.MODID.toLowerCase() + ".salt");
        this.setBlockTextureName(Agriculture.MODID.toLowerCase() + ":Salt");
        this.setCreativeTab(Agriculture.instance.creativeTabBlock);
        setSalt();
    }

    private void setSalt()
    {
        ItemStack saltStack = ItemList.getItemStack("base", "Salt");
        if (saltStack != null)
        {
            saltItem = saltStack.getItem();
            saltMeta = saltStack.getItemDamage();
        }
    }

    @Override
    public Item getItemDropped(int fortune, Random rand, int meta)
    {
        if (saltItem == null) return super.getItemDropped(fortune, rand, meta);

        return saltItem;
    }

    @Override
    public int damageDropped(int meta)
    {
        if (saltItem == null) return super.damageDropped(meta);

        return saltMeta;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        int droppedAmount = random.nextInt(fortune + 3 + 1) - 1;

        if (droppedAmount < 0)
        {
            droppedAmount = 0;
        }

        return (droppedAmount + 1);
    }

}
