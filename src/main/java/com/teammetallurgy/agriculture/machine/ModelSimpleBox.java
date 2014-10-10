package com.teammetallurgy.agriculture.machine;

import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ModelSimpleBox {
    public String field_78247_g;

    private final IIcon icon;

    /** X vertex coordinate of lower box corner */
    public final float posX1;

    /** X vertex coordinate of upper box corner */
    public final float posX2;

    /** Y vertex coordinate of lower box corner */
    public final float posY1;

    /** Y vertex coordinate of upper box corner */
    public final float posY2;

    /** Z vertex coordinate of lower box corner */
    public final float posZ1;

    /** Z vertex coordinate of upper box corner */
    public final float posZ2;
    /** An array of 6 TexturedQuads, one for each face of a cube */
    private final TexturedQuadIcon[] quadList;

    /**
     * The (x,y,z) vertex positions and (u,v) texture coordinates for each of
     * the 8 points on a cube
     */
    private final PositionTextureVertex[] vertexPositions;

    public ModelSimpleBox(final int textureWidth, final int textureHeight, final int textureX, final int textureZ, float posX, float posY, float posZ, final int width, final int height, final int width2, final int par10, final IIcon liquidIcon)
    {
        icon = liquidIcon;
        posX1 = posX;
        posY1 = posY;
        posZ1 = posZ;
        posX2 = posX + width;
        posY2 = posY + height;
        posZ2 = posZ + width2;
        vertexPositions = new PositionTextureVertex[8];
        quadList = new TexturedQuadIcon[6];
        float f4 = posX + width;
        float f5 = posY + height;
        float f6 = posZ + width2;
        posX -= par10;
        posY -= par10;
        posZ -= par10;
        f4 += par10;
        f5 += par10;
        f6 += par10;

        final PositionTextureVertex positiontexturevertex = new PositionTextureVertex(posX, posY, posZ, 0.0F, 0.0F);
        final PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(f4, posY, posZ, 0.0F, 8.0F);
        final PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(f4, f5, posZ, 8.0F, 8.0F);
        final PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(posX, f5, posZ, 8.0F, 0.0F);
        final PositionTextureVertex positiontexturevertex4 = new PositionTextureVertex(posX, posY, f6, 0.0F, 0.0F);
        final PositionTextureVertex positiontexturevertex5 = new PositionTextureVertex(f4, posY, f6, 0.0F, 8.0F);
        final PositionTextureVertex positiontexturevertex6 = new PositionTextureVertex(f4, f5, f6, 8.0F, 8.0F);
        final PositionTextureVertex positiontexturevertex7 = new PositionTextureVertex(posX, f5, f6, 8.0F, 0.0F);
        vertexPositions[0] = positiontexturevertex;
        vertexPositions[1] = positiontexturevertex1;
        vertexPositions[2] = positiontexturevertex2;
        vertexPositions[3] = positiontexturevertex3;
        vertexPositions[4] = positiontexturevertex4;
        vertexPositions[5] = positiontexturevertex5;
        vertexPositions[6] = positiontexturevertex6;
        vertexPositions[7] = positiontexturevertex7;
        quadList[0] = new TexturedQuadIcon(new PositionTextureVertex[] { positiontexturevertex5, positiontexturevertex1, positiontexturevertex2, positiontexturevertex6 }, textureX + width2 + width, textureZ + width2, textureX + width2 + width + width2, textureZ + width2 + height, textureWidth, textureHeight);
        quadList[1] = new TexturedQuadIcon(new PositionTextureVertex[] { positiontexturevertex, positiontexturevertex4, positiontexturevertex7, positiontexturevertex3 }, textureX, textureZ + width2, textureX + width2, textureZ + width2 + height, textureWidth, textureHeight);
        quadList[2] = new TexturedQuadIcon(new PositionTextureVertex[] { positiontexturevertex5, positiontexturevertex4, positiontexturevertex, positiontexturevertex1 }, textureX + width2, textureZ, textureX + width2 + width, textureZ + width2, textureWidth, textureHeight);
        quadList[3] = new TexturedQuadIcon(new PositionTextureVertex[] { positiontexturevertex2, positiontexturevertex3, positiontexturevertex7, positiontexturevertex6 }, textureX + width2 + width, textureZ + width2, textureX + width2 + width + width, textureZ, textureWidth, textureHeight);
        quadList[4] = new TexturedQuadIcon(new PositionTextureVertex[] { positiontexturevertex1, positiontexturevertex, positiontexturevertex3, positiontexturevertex2 }, textureX + width2, textureZ + width2, textureX + width2 + width, textureZ + width2 + height, textureWidth, textureHeight);
        quadList[5] = new TexturedQuadIcon(new PositionTextureVertex[] { positiontexturevertex4, positiontexturevertex5, positiontexturevertex6, positiontexturevertex7 }, textureX + width2 + width + width2, textureZ + width2, textureX + width2 + width + width2 + width, textureZ + width2 + height, textureWidth, textureHeight);
    }

    public ModelSimpleBox func_78244_a(final String par1Str)
    {
        field_78247_g = par1Str;
        return this;
    }

    /**
     * Draw the six sided box defined by this ModelBox
     */
    @SideOnly(Side.CLIENT)
    public void render(final Tessellator par1Tessellator, final float par2)
    {
        for (final TexturedQuadIcon element : quadList)
        {
            element.draw(par1Tessellator, par2, icon);
        }
    }
}
