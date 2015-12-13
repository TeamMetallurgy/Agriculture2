package com.teammetallurgy.agriculture.machine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiFuel extends GuiContainer
{

    private ResourceLocation texture = new ResourceLocation("agriculture:textures/gui/Fuel.png");
    private IFueledMachine machine;

    public GuiFuel(ContainerFuel container)
    {
        super(container);

        TileEntityBaseMachine tileEntity = container.getMachine();
        if (tileEntity instanceof IFueledMachine)
        {
            machine = (IFueledMachine) tileEntity;
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTicks, int mouseX, int mouseZ)
    {
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if (machine == null || !machine.isBurning()) { return; }

        int scale = machine.getScaledBurningTicks(13);
        drawTexturedModalRect(guiLeft + 81, guiTop + 22 + 12 - scale, 180, 4 + 12 - scale, 14, scale + 1);

    }

}
