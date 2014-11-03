package com.teammetallurgy.agriculture;

import com.teammetallurgy.agriculture.machine.brewer.BlockBrewer;
import com.teammetallurgy.agriculture.machine.brewer.TileEntityBrewer;
import com.teammetallurgy.agriculture.machine.counter.TileEntityCounter;
import com.teammetallurgy.agriculture.machine.frier.TileEntityFrier;
import com.teammetallurgy.agriculture.machine.icebox.TileEntityIcebox;
import com.teammetallurgy.agriculture.machine.oven.TileEntityOven;
import com.teammetallurgy.agriculture.machine.processor.TileEntityProcessor;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class BlockList
{

    public static Block brewer;
    public static Block counter;
    public static Block frier;
    public static Block icebox;
    public static Block oven;
    public static Block processor;
    
    public static void preInit()
    {
        String modID = Agriculture.MODID.toLowerCase();
        
        String blockName = "brewer";
        brewer = new BlockBrewer().setBlockName(modID + "." + blockName);
        GameRegistry.registerBlock(brewer, blockName);
        GameRegistry.registerTileEntity(TileEntityBrewer.class, blockName);
        
        blockName = "counter";
        counter = new BlockBrewer().setBlockName(modID + "." + blockName);
        GameRegistry.registerBlock(counter, blockName);
        GameRegistry.registerTileEntity(TileEntityCounter.class, blockName);
        
        blockName = "frier";
        frier = new BlockBrewer().setBlockName(modID + "." + blockName);
        GameRegistry.registerBlock(frier, blockName);
        GameRegistry.registerTileEntity(TileEntityFrier.class, blockName);
        
        blockName = "icebox";
        icebox = new BlockBrewer().setBlockName(modID + "." + blockName);
        GameRegistry.registerBlock(icebox, blockName);
        GameRegistry.registerTileEntity(TileEntityIcebox.class, blockName);
        
        blockName = "oven";
        oven = new BlockBrewer().setBlockName(modID + "." + blockName);
        GameRegistry.registerBlock(oven, blockName);
        GameRegistry.registerTileEntity(TileEntityOven.class, blockName);
        
        blockName = "processor";
        processor = new BlockBrewer().setBlockName(modID + "." + blockName);
        GameRegistry.registerBlock(processor, blockName);
        GameRegistry.registerTileEntity(TileEntityProcessor.class, blockName);
        
    }

}
