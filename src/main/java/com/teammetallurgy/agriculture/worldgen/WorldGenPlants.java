package com.teammetallurgy.agriculture.worldgen;

import java.util.Random;

import com.teammetallurgy.agriculture.BlockList;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenPlants implements IWorldGenerator
{
    private Block plants[];
    private static long plantGenerationSeed;

    public WorldGenPlants()
    {
        plantGenerationSeed = "agriculture.plants".hashCode();
        plants = new Block[] { BlockList.cinnamon, BlockList.peanut, BlockList.strawberry, BlockList.vanilla };
    }

    @Override
    public void generate(Random fmlRandom, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        Random random = new Random(((fmlRandom.nextLong() ^ plantGenerationSeed) ^ chunkX) ^ chunkZ);
        
        if (random.nextInt(100) > 75) return;
        
        int xPos = (chunkX * 16) + random.nextInt(16);
        int zPos = (chunkZ * 16) + random.nextInt(16);
        int yPos = world.getHeightValue(xPos, zPos) + 32;

        do
        {
            Block block = world.getBlock(xPos, yPos, zPos);
            if (!(block.isLeaves(world, xPos, yPos, zPos) || block.isAir(world, xPos, yPos, zPos)))
            {
                break;
            }
            --yPos;
        } while (yPos > 0);

        yPos++;

        Block blockPlant = plants[random.nextInt(plants.length)];

        int radius = random.nextInt(6) + 1;

        for (int i = 0; i < radius; i++)
        {
            for (int j = 0; j < radius; j++)
            {
                if (world.isAirBlock(xPos + i, yPos, zPos + j) && blockPlant.canBlockStay(world, xPos + i, yPos, zPos + j) && random.nextInt(100) > 75)
                {
                    world.setBlock(xPos + i, yPos, zPos + j, blockPlant, 0, 2);
                }
            }

        }

    }

}
