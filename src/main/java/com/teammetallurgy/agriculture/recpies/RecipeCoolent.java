package com.teammetallurgy.agriculture.recpies;

import net.minecraft.item.ItemStack;

public class RecipeCoolent
{
    private ItemStack[] coolents;
    private int ticks;
    private int temp;

    public RecipeCoolent(ItemStack coolent, int ticks, int temp)
    {
        coolents = new ItemStack[] { coolent };
        this.ticks = ticks;
        this.temp = temp;
    }

    public RecipeCoolent(ItemStack[] coolents, int ticks, int temp)
    {
        this.coolents = coolents;
        this.ticks = ticks;
        this.temp = temp;
    }

    public ItemStack[] getCoolents()
    {
        return coolents.clone();
    }

    public int getTicks()
    {
        return ticks;
    }

    public int getTemp()
    {
        return temp;
    }
}
