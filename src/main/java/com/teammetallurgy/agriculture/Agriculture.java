package com.teammetallurgy.agriculture;

import net.minecraft.creativetab.CreativeTabs;

import com.teammetallurgy.agriculture.networking.CommonProxy;
import com.teammetallurgy.metallurgycore.CreativeTab;
import com.teammetallurgy.metallurgycore.handlers.ConfigHandler;
import com.teammetallurgy.metallurgycore.handlers.LogHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

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

    public CreativeTabs creativeTabFood = new CreativeTab(Agriculture.MODID + ".Food");
    public CreativeTabs creativeTabBlock = new CreativeTab(Agriculture.MODID + ".blocks");

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(Agriculture.instance, Agriculture.proxy);
        ItemList.initRecipes();
        ItemList.recalculateValues();
        proxy.initRenderers();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LogHandler.setLog(event.getModLog());
        ConfigHandler.setFile(event.getSuggestedConfigurationFile());

        ItemList.preInit();

        BlockList.preInit();
    }
}
