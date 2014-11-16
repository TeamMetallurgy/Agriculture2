package com.teammetallurgy.agriculture.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import com.teammetallurgy.agriculture.BlockList;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenSalt implements IWorldGenerator
{
    private int saltPerChunk = 1;
    private Block saltBlock;
    private static int saltSeed = "saltBlock".hashCode();

    public WorldGenSalt()
    {
        saltBlock = BlockList.salt;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {

        Random chunkRandom = new Random(random.nextLong() + saltSeed);

        for (int i = 0; i < saltPerChunk; i++)
        {
            int randX = chunkX * 16 + chunkRandom.nextInt(16) + 8;
            int randZ = chunkZ * 16 + chunkRandom.nextInt(16) + 8;
            this.generate(world, chunkRandom, randX, world.getTopSolidOrLiquidBlock(randX, randZ), randZ);
        }
    }

    public boolean generate(World world, Random random, int centerX, int centerY, int centerZ)
    {
        if (world.getBlock(centerX, centerY, centerZ).getMaterial() != Material.water)
        {
            return false;
        }
        else
        {
            int raidus = random.nextInt(2) + 2;
            byte heightRaidus = 1;

            for (int posX = centerX - raidus; posX <= centerX + raidus; posX++)
            {
                for (int posZ = centerZ - raidus; posZ <= centerZ + raidus; posZ++)
                {
                    int deltaX = posX - centerX;
                    int deltaZ = posZ - centerZ;

                    if (deltaX * deltaX + deltaZ * deltaZ <= raidus * raidus)
                    {
                        for (int posY = centerY - heightRaidus; posY <= centerY + heightRaidus; posY++)
                        {
                            Block block = world.getBlock(posX, posY, posZ);

                            if (block == Blocks.dirt || block == saltBlock)
                            {
                                world.setBlock(posX, posY, posZ, saltBlock, 0, 2);
                            }
                        }
                    }
                }
            }
            return true;
        }
    }
}
