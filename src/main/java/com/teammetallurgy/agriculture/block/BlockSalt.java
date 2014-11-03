package com.teammetallurgy.agriculture.block;

import com.teammetallurgy.agriculture.Agriculture;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSalt extends Block
{

    public BlockSalt()
    {
        super(Material.rock);
        this.setHardness(1.5F);
        this.setBlockName(Agriculture.MODID.toLowerCase() + ".salt");
        this.setBlockTextureName(Agriculture.MODID.toLowerCase() +":Salt");
        this.setCreativeTab(Agriculture.instance.creativeTabBlock);
    }

}
