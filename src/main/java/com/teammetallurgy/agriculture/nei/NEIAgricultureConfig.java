package com.teammetallurgy.agriculture.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIAgricultureConfig implements IConfigureNEI
{

    @Override
    public void loadConfig()
    {
        API.registerRecipeHandler(new BrewerRecipeHandler());
        API.registerUsageHandler(new BrewerRecipeHandler());

        API.registerRecipeHandler(new CounterRecipeHandler());
        API.registerUsageHandler(new CounterRecipeHandler());

        API.registerRecipeHandler(new FrierRecipeHandler());
        API.registerUsageHandler(new FrierRecipeHandler());

        API.registerRecipeHandler(new IceBoxRecipeHandler());
        API.registerUsageHandler(new IceBoxRecipeHandler());

        API.registerRecipeHandler(new OvenRecipeHandler());
        API.registerUsageHandler(new OvenRecipeHandler());

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
