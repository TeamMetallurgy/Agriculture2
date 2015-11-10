package com.teammetallurgy.agriculture.nei;

import codechicken.nei.NEIClientUtils;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class OvenRecipeHandler extends TemplateRecipeHandler
{

    @Override
    public String getRecipeName()
    {
        return NEIClientUtils.translate("recipe.agriculture.oven");
    }

    @Override
    public String getGuiTexture()
    {
        return "agriculture:textures/gui/OvenNEI.png";
    }

    @Override
    public String getOverlayIdentifier()
    {
        return "agriculture.oven";
    }

}
