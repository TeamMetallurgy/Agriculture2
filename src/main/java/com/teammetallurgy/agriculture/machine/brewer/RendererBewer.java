package com.teammetallurgy.agriculture.machine.brewer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RendererBewer extends TileEntitySpecialRenderer
{

    private ResourceLocation texture = new ResourceLocation("agriculture:textures/models/machines/brewer.png");
    private ModelBrewer model = new ModelBrewer();

    public void renderTileEntityAt(TileEntityBrewer tileEntity, double xPos, double yPos, double zPos, float renderPartialTicks)
    {
        float rotation = tileEntity.getFacing() * 90.0F;
        GL11.glPushMatrix();
        GL11.glTranslatef((float) xPos + 0.5F, (float) yPos + 1.5F, (float) zPos + 0.5F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
        this.bindTexture(texture);
        this.model.renderAll();
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double xPos, double yPos, double zPos, float renderPartialTicks)
    {
        this.renderTileEntityAt((TileEntityBrewer) tileEntity, xPos, yPos, zPos, renderPartialTicks);
    }

}
