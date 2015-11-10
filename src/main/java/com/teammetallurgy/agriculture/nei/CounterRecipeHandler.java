package com.teammetallurgy.agriculture.nei;

import codechicken.nei.NEIClientUtils;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class CounterRecipeHandler extends TemplateRecipeHandler
{

    @Override
    public String getRecipeName()
    {
        return NEIClientUtils.translate("recipe.agriculture.counter");
    }

    @Override
    public String getGuiTexture()
    {
        return "agriculture:textures/gui/CounterNEI.png";
    }

    @Override
    public String getOverlayIdentifier()
    {
        return "agriculture.counter";
    }
}
