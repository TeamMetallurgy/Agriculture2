package com.teammetallurgy.agriculture.food;

public class Food
{
    public static enum FoodType
    {
        base, edible
    }

    public static enum Methods
    {
        process(2), bake(3), prepare(1), brew(2), fry(3), freeze(3);

        private int hungerBonus;

        private Methods(int bonus)
        {
            hungerBonus = bonus;
        }

        public int getHungerBonus()
        {
            return hungerBonus;
        }
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
