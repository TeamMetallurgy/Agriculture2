package com.teammetallurgy.agriculture.food;

public class Food
{
    public static enum FoodType
    {
        base, edible
    }

    public static enum Methods
    {
        process, bake, prepare, brew, fry, freeze
    }

    private String name;

    public FoodType type;

    public Methods method;

    public String[] recipe;

    public int hungerPoints;

    public String getName()
    {
        return this.name;
    }
}
