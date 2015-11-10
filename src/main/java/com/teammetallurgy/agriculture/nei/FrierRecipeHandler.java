package com.teammetallurgy.agriculture.nei;

import codechicken.nei.NEIClientUtils;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class FrierRecipeHandler extends TemplateRecipeHandler
{

    @Override
    public String getRecipeName()
    {
        return NEIClientUtils.translate("recipe.agriculture.frier");
    }

    @Override
    public String getGuiTexture()
    {
        return "agriculture:textures/gui/FrierNEI.png";
    }
    
    @Override
    public String getOverlayIdentifier()
    {     
        return "agriculture.frier";
    }
}
