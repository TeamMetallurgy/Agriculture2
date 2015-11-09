package com.teammetallurgy.agriculture.machine.counter;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiCounter extends GuiContainer
{

    ResourceLocation texture = new ResourceLocation("agriculture:textures/gui/Counter.png");

    public GuiCounter(ContainerCounter container)
    {
        super(container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTicks, int mouseX, int mouseZ)
    {
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

}
