package com.teammetallurgy.agriculture.nei;

import codechicken.nei.NEIClientUtils;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class IceBoxRecipeHandler extends TemplateRecipeHandler
{

    @Override
    public String getRecipeName()
    {
        return NEIClientUtils.translate("recipe.agriculture.icebox");
    }

    @Override
    public String getGuiTexture()
    {
        return "agriculture:textures/gui/IceboxNEI.png";
    }
    
    @Override
    public String getOverlayIdentifier()
    {     
        return "agriculture.icebox";
    }

}
