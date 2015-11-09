package com.teammetallurgy.agriculture.machine.processor;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiProcessor extends GuiContainer
{

    private TileEntityProcessor machine;
    private ResourceLocation texture = new ResourceLocation("agriculture:textures/gui/Processor.png");

    public GuiProcessor(ContainerProcessor container)
    {
        super(container);
        machine = container.getMachine();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTicks, int mouseX, int mouseZ)
    {
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if (machine.processingTicks > 0)
        {
            int scaled = machine.getScaledProcessingTime(22);
            drawTexturedModalRect(guiLeft + 96, guiTop + 37, 177, 11, scaled, 5);
        }
    }

}
