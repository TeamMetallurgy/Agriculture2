package com.teammetallurgy.agriculture.worldgen;

import com.teammetallurgy.agriculture.BlockList;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenSalt extends WorldGenerator implements IWorldGenerator
{
    private int saltPerChunk = 1;
    private Block saltBlock;

    public WorldGenSalt()
    {
        saltBlock = BlockList.salt;
    }

    @Override
    public void generate(final Random randomGenerator, final int chunkX, final int chunkZ, final World world, final IChunkProvider chunkGenerator, final IChunkProvider chunkProvider)
    {
        for (int i = 0; i < saltPerChunk; ++i)
        {
            int randX = chunkX * 16 + randomGenerator.nextInt(16) + 8;
            int randZ = chunkZ * 16 + randomGenerator.nextInt(16) + 8;
            this.generate(world, randomGenerator, randX, world.getTopSolidOrLiquidBlock(randX, randZ), randZ);
        }
    }

    @Override
    public boolean generate(final World par1World, final Random par2Random, final int par3, final int par4, final int par5)
    {
        if (par1World.getBlock(par3, par4, par5).getMaterial() != Material.water)
        {
            return false;
        }
        else
        {
            int l = par2Random.nextInt(2) + 2;
            byte b0 = 1;

            for (int i1 = par3 - l; i1 <= par3 + l; ++i1)
            {
                for (int j1 = par5 - l; j1 <= par5 + l; ++j1)
                {
                    int k1 = i1 - par3;
                    int l1 = j1 - par5;

                    if (k1 * k1 + l1 * l1 <= l * l)
                    {
                        for (int i2 = par4 - b0; i2 <= par4 + b0; ++i2)
                        {
                            Block j2 = par1World.getBlock(i1, i2, j1);

                            if (j2 == Blocks.dirt || j2 == Blocks.clay)
                            {
                                par1World.setBlock(i1, i2, j1, saltBlock, 0, 2);
                            }
                        }
                    }
                }
            }
            return true;
        }
    }
}
