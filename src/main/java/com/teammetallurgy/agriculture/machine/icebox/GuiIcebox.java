package com.teammetallurgy.agriculture.machine.icebox;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiIcebox extends GuiContainer
{

    private ResourceLocation texture = new ResourceLocation("agriculture:textures/gui/Icebox.png");

    public GuiIcebox(ContainerIcebox container)
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
