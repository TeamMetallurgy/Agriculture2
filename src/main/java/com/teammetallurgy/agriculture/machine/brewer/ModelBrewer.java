package com.teammetallurgy.agriculture.machine.brewer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidStack;

import com.teammetallurgy.agriculture.machine.ModelSimpleBox;

public class ModelBrewer extends ModelBase {
    // fields
    ModelRenderer base;
    ModelRenderer base2;
    ModelSimpleBox filling;
    ModelSimpleBox liquid;
    private IIcon liquidIcon;

    ModelRenderer nozzle;

    ModelRenderer top;
    ModelRenderer tophatch;

    public ModelBrewer()
    {
        textureWidth = 128;
        textureHeight = 64;

        base = new ModelRenderer(this, 0, 36);
        base.addBox(-8F, -8F, -8F, 16, 12, 15);
        base.setRotationPoint(0F, 20F, 1F);
        base.setTextureSize(128, 64);
        base.mirror = true;
        setRotation(base, 0F, 0F, 0F);
        top = new ModelRenderer(this, 0, 12);
        top.addBox(-8F, -2F, -8F, 16, 3, 16);
        top.setRotationPoint(0F, 11F, 0F);
        top.setTextureSize(128, 64);
        top.mirror = true;
        setRotation(top, 0F, 0F, 0F);
        nozzle = new ModelRenderer(this, 0, 0);
        nozzle.addBox(-1F, 0F, -1F, 2, 3, 1);
        nozzle.setRotationPoint(0F, 21F, -7F);
        nozzle.setTextureSize(128, 64);
        nozzle.mirror = true;
        setRotation(nozzle, 0F, 0F, 0F);
        tophatch = new ModelRenderer(this, 14, 0);
        tophatch.addBox(-5F, -1F, -10F, 10, 1, 10);
        tophatch.setRotationPoint(0F, 9F, 5F);
        tophatch.setTextureSize(128, 64);
        tophatch.mirror = true;
        setRotation(tophatch, 0F, 0F, 0F);
    }

    public void getLiquidIcon(final FluidStack fluid)
    {
        if (fluid != null)
        {
            final IIcon stillIcon = fluid.getFluid().getStillIcon();
            if (stillIcon != null)
            {
                liquidIcon = stillIcon;
            }
        }
    }

    @Override
    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        base.render(f5);
        top.render(f5);
        nozzle.render(f5);
        tophatch.render(f5);
    }

    public void renderAll()
    {
        base.render(1 / 16f);
        top.render(1 / 16f);
        nozzle.render(1 / 16f);
        tophatch.render(1 / 16f);
    }

    public void renderLiquids()
    {
        if (liquid != null)
        {
            liquid.render(Tessellator.instance, 1 / 16f);
        }

        if (filling != null)
        {
            filling.render(Tessellator.instance, 1 / 16f);
        }
    }

    public void setDoorAngle(final float angle)
    {
        tophatch.rotateAngleX = 1.5F * -angle;
    }

    public void setFlowing(final int i)
    {
        if (i > 0)
        {
            float scaled = i / 1000f;

            scaled = scaled > 1 ? 1 : scaled;

            final int width = (int) (3 * scaled);
            final int height = 10;

            final int x1 = (int) -(width / 2f);
            final int y1 = 12;
            final int z1 = (int) -(width / 2f);

            if (filling == null)
            {
                filling = new ModelSimpleBox(16, 16, 0, 0, x1, y1, z1, width, height, width, 0, liquidIcon);
            }
        }
        else
        {
            filling = null;
        }
    }

    public void setLiquidLevel(float level)
    {
        if (level > 0)
        {
            if (level > 1)
            {
                level = 1;
            }

            final int height = (int) (level * 12);
            liquid = new ModelSimpleBox(16, 16, 0, 0, -7, 23 - height, -6, 14, height, 13, 0, liquidIcon);
        }
        else
        {
            liquid = null;
        }
    }

    private void setRotation(final ModelRenderer model, final float x, final float y, final float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
