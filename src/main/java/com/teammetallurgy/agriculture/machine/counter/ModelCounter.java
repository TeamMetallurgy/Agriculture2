package com.teammetallurgy.agriculture.machine.counter;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCounter extends ModelBase {
    ModelRenderer countertop;
    // fields
    ModelRenderer ovenbase;
    ModelRenderer ovendoor1;
    ModelRenderer ovendoor2;

    public ModelCounter()
    {
        textureWidth = 128;
        textureHeight = 64;

        ovenbase = new ModelRenderer(this, 0, 36);
        ovenbase.addBox(-8F, -8F, -8F, 16, 12, 15);
        ovenbase.setRotationPoint(0F, 20F, 1F);
        ovenbase.setTextureSize(128, 64);
        ovenbase.mirror = true;
        setRotation(ovenbase, 0F, 0F, 0F);
        ovendoor1 = new ModelRenderer(this, 31, 0);
        ovendoor1.addBox(-6F, -4F, -1F, 6, 8, 1);
        ovendoor1.setRotationPoint(6F, 18F, -7F);
        ovendoor1.setTextureSize(128, 64);
        ovendoor1.mirror = true;
        setRotation(ovendoor1, 0F, 0F, 0F);
        countertop = new ModelRenderer(this, 0, 12);
        countertop.addBox(-8F, -2F, -8F, 16, 4, 16);
        countertop.setRotationPoint(0F, 10F, 0F);
        countertop.setTextureSize(128, 64);
        countertop.mirror = true;
        setRotation(countertop, 0F, 0F, 0F);
        ovendoor2 = new ModelRenderer(this, 0, 0);
        ovendoor2.addBox(0F, -4F, -1F, 6, 8, 1);
        ovendoor2.setRotationPoint(-6F, 18F, -7F);
        ovendoor2.setTextureSize(128, 64);
        ovendoor2.mirror = true;
        setRotation(ovendoor2, 0F, 0F, 0F);
    }

    @Override
    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        ovenbase.render(f5);
        ovendoor1.render(f5);
        countertop.render(f5);
        ovendoor2.render(f5);
    }

    public void renderAll()
    {
        ovenbase.render(1 / 16f);
        ovendoor1.render(1 / 16f);
        countertop.render(1 / 16f);
        ovendoor2.render(1 / 16f);
    }

    public void setDoorAngle(final float angle)
    {
        ovendoor1.rotateAngleY = 1.5F * -angle;
        ovendoor2.rotateAngleY = 1.5F * angle;
    }

    private void setRotation(final ModelRenderer model, final float x, final float y, final float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
