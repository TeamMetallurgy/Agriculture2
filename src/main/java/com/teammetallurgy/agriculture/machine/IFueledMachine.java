package com.teammetallurgy.agriculture.machine;

public interface IFueledMachine
{
    public boolean isBurning();

    public int getScaledBurningTicks(int scale);
}
