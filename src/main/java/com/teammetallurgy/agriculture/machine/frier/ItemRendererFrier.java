package com.teammetallurgy.agriculture.machine.frier;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.teammetallurgy.agriculture.BlockList;

import cpw.mods.fml.client.FMLClientHandler;

public class ItemRendererFrier implements IItemRenderer
{

    private ResourceLocation texture = new ResourceLocation("agriculture:textures/models/machines/frier.png");
    private ModelFrier model = new ModelFrier();

    @Override
    public boolean handleRenderType(ItemStack itemStack, ItemRenderType type)
    {
        if (itemStack == null || itemStack.getItem() == null) return false;

        if (BlockList.frier != Block.getBlockFromItem(itemStack.getItem())) return false;

        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack itemStack, ItemRendererHelper helper)
    {
        if (itemStack == null || itemStack.getItem() == null) return false;

        if (BlockList.frier != Block.getBlockFromItem(itemStack.getItem())) return false;

        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(0.5F, 1.4F, 0.5F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        if (type == ItemRenderType.EQUIPPED)
        {
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        }
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
        this.model.setDoorAngle(0);
        this.model.renderAll();
        GL11.glPopMatrix();
    }

}
