package com.teammetallurgy.agriculture.cooking;

import com.teammetallurgy.agriculture.food.Food;

/**
 * Created by freyja
 */
public class MethodFactory
{
    public static IMethod getMethod(Food.Methods methods)
    {
        switch(methods)
        {
            case bake:
                return new MethodBake();
            case process:
                return new MethodProcess();
        }
        return null;
    }
}
