package com.teammetallurgy.agriculture.networking;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

import com.teammetallurgy.agriculture.BlockList;
import com.teammetallurgy.agriculture.machine.brewer.ItemRendererBrewer;
import com.teammetallurgy.agriculture.machine.brewer.RendererBewer;
import com.teammetallurgy.agriculture.machine.brewer.TileEntityBrewer;
import com.teammetallurgy.agriculture.machine.counter.ItemRendererCounter;
import com.teammetallurgy.agriculture.machine.counter.RendererCounter;
import com.teammetallurgy.agriculture.machine.counter.TileEntityCounter;
import com.teammetallurgy.agriculture.machine.frier.ItemRendererFrier;
import com.teammetallurgy.agriculture.machine.frier.RendererFrier;
import com.teammetallurgy.agriculture.machine.frier.TileEntityFrier;
import com.teammetallurgy.agriculture.machine.icebox.ItemRendererIcebox;
import com.teammetallurgy.agriculture.machine.icebox.RendererIcebox;
import com.teammetallurgy.agriculture.machine.icebox.TileEntityIcebox;
import com.teammetallurgy.agriculture.machine.oven.ItemRendererOven;
import com.teammetallurgy.agriculture.machine.oven.RendererOven;
import com.teammetallurgy.agriculture.machine.oven.TileEntityOven;
import com.teammetallurgy.agriculture.machine.processor.ItemRendererProcessor;
import com.teammetallurgy.agriculture.machine.processor.RendererProcessor;
import com.teammetallurgy.agriculture.machine.processor.TileEntityProcessor;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void initRenderers()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBrewer.class, new RendererBewer());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockList.brewer), new ItemRendererBrewer());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCounter.class, new RendererCounter());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockList.counter), new ItemRendererCounter());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFrier.class, new RendererFrier());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockList.frier), new ItemRendererFrier());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityIcebox.class, new RendererIcebox());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockList.icebox), new ItemRendererIcebox());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOven.class, new RendererOven());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockList.oven), new ItemRendererOven());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityProcessor.class, new RendererProcessor());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockList.processor), new ItemRendererProcessor());
    }
}
