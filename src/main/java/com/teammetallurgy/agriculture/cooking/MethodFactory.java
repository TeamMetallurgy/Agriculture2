package com.teammetallurgy.agriculture.cooking;

import com.teammetallurgy.agriculture.food.Food;

/**
 * Created by freyja
 */
public class MethodFactory
{
    public static IMethod getMethod(Food.Methods methods)
    {
        switch (methods)
        {
            case bake:
                return new MethodBake();
            case brew:
                return new MethodBrew();
            case freeze:
                return new MethodFreeze();
            case fry:
                return new MethodFry();
            case prepare:
                return new MethodPrepair();
            case process:
                return new MethodProcess();
            default:
                return null;
        }
    }
}
