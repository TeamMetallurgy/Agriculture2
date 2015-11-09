package com.teammetallurgy.agriculture;

import net.minecraftforge.common.MinecraftForge;

import com.teammetallurgy.agriculture.handler.AgricultureConfigHandler;
import com.teammetallurgy.agriculture.handler.AgricultureEventHandler;
import com.teammetallurgy.agriculture.handler.AgricultureLogHandler;
import com.teammetallurgy.agriculture.handler.GuiHandler;
import com.teammetallurgy.agriculture.handler.MissingMapHandler;
import com.teammetallurgy.agriculture.hunger.Hunger;
import com.teammetallurgy.agriculture.networking.CommonProxy;
import com.teammetallurgy.agriculture.recpies.Recipes;
import com.teammetallurgy.agriculture.utils.AgricultureCreativeTab;
import com.teammetallurgy.agriculture.worldgen.WorldGenPlants;
import com.teammetallurgy.agriculture.worldgen.WorldGenSalt;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(name = Agriculture.MODNAME, modid = Agriculture.MODID, version = Agriculture.VERSION, useMetadata = true)
public class Agriculture
{
    public static final String MODNAME = "Agriculture";
    public static final String MODID = "Agriculture";
    public static final String VERSION = "2.0";

    @Mod.Instance(Agriculture.MODID)
    public static Agriculture instance;

    @SidedProxy(clientSide = "com.teammetallurgy.agriculture.networking.ClientProxy", serverSide = "com.teammetallurgy.agriculture.networking.CommonProxy")
    public static CommonProxy proxy;
    

    public AgricultureCreativeTab creativeTabFood = new AgricultureCreativeTab(Agriculture.MODID + ".Food");
    public AgricultureCreativeTab creativeTabBlock = new AgricultureCreativeTab(Agriculture.MODID + ".blocks");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        AgricultureLogHandler.setLogger(event.getModLog());
        AgricultureConfigHandler.setFile(event.getSuggestedConfigurationFile());

        ItemList.preInit();

        BlockList.preInit();

        setCreativeTabsIcons();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(Agriculture.instance, new GuiHandler());
        GameRegistry.registerWorldGenerator(new WorldGenSalt(), 0);
        GameRegistry.registerWorldGenerator(new WorldGenPlants(), 0);
        proxy.initRenderers();
        MinecraftForge.EVENT_BUS.register(new AgricultureEventHandler());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        Recipes.create();
        Hunger.init();
    }

    @EventHandler
    public void missingMapping(FMLMissingMappingsEvent event)
    {
        MissingMapHandler.processingMissingMap(event);
    }

    private void setCreativeTabsIcons()
    {
        
        creativeTabFood.setItemStack(ItemList.getItemStack("base", "Caramel Apple With Nuts"));
        creativeTabBlock.setItem(BlockList.counter);
    }
}
