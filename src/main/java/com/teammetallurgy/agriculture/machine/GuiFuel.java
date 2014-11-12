package com.teammetallurgy.agriculture.machine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class GuiFuel extends GuiContainer
{

    private ResourceLocation texture = new ResourceLocation("agriculture:textures/gui/Fuel.png");

    public GuiFuel(ContainerFuel container)
    {
        super(container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        GL11.glEnable(GL11.GL_LIGHTING);

    }

}
