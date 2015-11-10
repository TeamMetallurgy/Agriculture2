package com.teammetallurgy.agriculture.nei;

import codechicken.nei.NEIClientUtils;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class BrewerRecipeHandler extends TemplateRecipeHandler
{

    @Override
    public String getRecipeName()
    {
        return NEIClientUtils.translate("recipe.agriculture.brewer");
    }

    @Override
    public String getGuiTexture()
    {
        return "agriculture:textures/gui/Brewer.png";
    }

    @Override
    public String getOverlayIdentifier()
    {
        return "agriculture.brewer";
    }

}
