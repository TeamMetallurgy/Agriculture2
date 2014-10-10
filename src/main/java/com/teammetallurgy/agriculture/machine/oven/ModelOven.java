package com.teammetallurgy.agriculture.machine.oven;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOven extends ModelBase {
    ModelRenderer countertop;
    ModelRenderer ovenbase;
    ModelRenderer ovendoor;

    public ModelOven()
    {
        textureWidth = 128;
        textureHeight = 64;

        ovenbase = new ModelRenderer(this, 0, 36);
        ovenbase.addBox(-8F, -8F, -8F, 16, 12, 15);
        ovenbase.setRotationPoint(0F, 20F, 1F);
        ovenbase.setTextureSize(128, 64);
        ovenbase.mirror = true;
        setRotation(ovenbase, 0F, 0F, 0F);
        ovendoor = new ModelRenderer(this, 0, 0);
        ovendoor.addBox(-6F, -8F, -1F, 12, 8, 1);
        ovendoor.setRotationPoint(0F, 22F, -7F);
        ovendoor.setTextureSize(128, 64);
        ovendoor.mirror = true;
        setRotation(ovendoor, 0F, 0F, 0F);
        countertop = new ModelRenderer(this, 0, 12);
        countertop.addBox(-8F, -2F, -8F, 16, 4, 16);
        countertop.setRotationPoint(0F, 10F, 0F);
        countertop.setTextureSize(128, 64);
        countertop.mirror = true;
        setRotation(countertop, 0F, 0F, 0F);
    }

    @Override
    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        ovenbase.render(f5);
        ovendoor.render(f5);
        countertop.render(f5);
    }

    public void renderAll()
    {
        ovenbase.render(1 / 16F);
        ovendoor.render(1 / 16F);
        countertop.render(1 / 16F);
    }

    public void setDoorAngle(final float amount)
    {
        ovendoor.rotateAngleX = 1.5f * amount;
    }

    private void setRotation(final ModelRenderer model, final float x, final float y, final float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

}
