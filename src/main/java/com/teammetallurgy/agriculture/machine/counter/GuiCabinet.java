package com.teammetallurgy.agriculture.machine.counter;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiCabinet extends GuiContainer
{

    ResourceLocation texture = new ResourceLocation("agriculture:textures/gui/Cabinet.png");

    public GuiCabinet(ContainerCabinet containerCabinet)
    {
        super(containerCabinet);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTicks, int mouseX, int mouseZ)
    {
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

}
