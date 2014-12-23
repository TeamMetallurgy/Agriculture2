package com.teammetallurgy.agriculture;

import org.apache.logging.log4j.Logger;

import com.teammetallurgy.agriculture.handler.AgricultureConfigHandler;
import com.teammetallurgy.agriculture.handler.GuiHandler;
import com.teammetallurgy.agriculture.networking.CommonProxy;
import com.teammetallurgy.agriculture.recpies.Recipes;
import com.teammetallurgy.agriculture.worldgen.WorldGenPlants;
import com.teammetallurgy.agriculture.worldgen.WorldGenSalt;
import com.teammetallurgy.metallurgycore.CreativeTab;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
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
    public static Logger logger;

    public CreativeTab creativeTabFood = new CreativeTab(Agriculture.MODID + ".Food");
    public CreativeTab creativeTabBlock = new CreativeTab(Agriculture.MODID + ".blocks");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        AgricultureConfigHandler.setFile(event.getSuggestedConfigurationFile());

        ItemList.preInit();

        BlockList.preInit();

        setCreativeTabsIcons();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(Agriculture.instance, new GuiHandler());
        ItemList.initRecipes();
        ItemList.recalculateValues();
        GameRegistry.registerWorldGenerator(new WorldGenSalt(), 0);
        GameRegistry.registerWorldGenerator(new WorldGenPlants(), 0);
        proxy.initRenderers();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        Recipes.createRecipes();
    }

    private void setCreativeTabsIcons()
    {
        creativeTabFood.setItemStack(ItemList.getItemStack("base", "CaramelAppleWithNuts"));
        creativeTabBlock.setItem(BlockList.counter);
    }
}
