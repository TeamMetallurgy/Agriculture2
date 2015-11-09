package com.teammetallurgy.agriculture.machine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiFuel extends GuiContainer
{

    private ResourceLocation texture = new ResourceLocation("agriculture:textures/gui/Fuel.png");

    public GuiFuel(ContainerFuel container)
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
