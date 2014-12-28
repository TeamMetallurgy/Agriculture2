package com.teammetallurgy.agriculture.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIAgricultureConfig implements IConfigureNEI
{

    @Override
    public void loadConfig()
    {
        // TODO Add handlers
        API.registerRecipeHandler(new ProcessorRecipeHandler());
        API.registerUsageHandler(new ProcessorRecipeHandler());
    }

    @Override
    public String getName()
    {
        return "Agriculture 2 NEI Plugin";
    }

    @Override
    public String getVersion()
    {
        return "2.0";
    }

}
