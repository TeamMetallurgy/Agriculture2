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
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        for (int i = 0; i < saltPerChunk; ++i)
        {
            int randX = chunkX * 16 + random.nextInt(16) + 8;
            int randZ = chunkZ * 16 + random.nextInt(16) + 8;
            this.generate(world, random, randX, world.getTopSolidOrLiquidBlock(randX, randZ), randZ);
        }
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        if (world.getBlock(x, y, z).getMaterial() != Material.water)
        {
            return false;
        }
        else
        {
            int l = random.nextInt(2) + 2;
            byte b0 = 1;

            for (int i1 = x - l; i1 <= x + l; ++i1)
            {
                for (int j1 = z - l; j1 <= z + l; ++j1)
                {
                    int k1 = i1 - x;
                    int l1 = j1 - z;

                    if (k1 * k1 + l1 * l1 <= l * l)
                    {
                        for (int i2 = y - b0; i2 <= y + b0; ++i2)
                        {
                            Block j2 = world.getBlock(i1, i2, j1);

                            if (j2 == Blocks.dirt || j2 == Blocks.clay)
                            {
                                world.setBlock(i1, i2, j1, saltBlock, 0, 2);
                            }
                        }
                    }
                }
            }
            return true;
        }
    }
}
