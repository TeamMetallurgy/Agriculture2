package com.teammetallurgy.agriculture.utils;

import net.minecraftforge.common.util.ForgeDirection;

public enum AgricultureDirection
{
    SOUTH(0), WEST(1), NORTH(2), EAST(3);

    private int directionIndex;

    private AgricultureDirection(int index)
    {
        directionIndex = index;
    }

    public static ForgeDirection convertToForgeDirection(AgricultureDirection direction)
    {
        ForgeDirection forgeDirection = null;
        switch (direction)
        {
            case SOUTH:
                forgeDirection = ForgeDirection.SOUTH;
                break;
            case WEST:
                forgeDirection = ForgeDirection.WEST;
                break;
            case NORTH:
                forgeDirection = ForgeDirection.NORTH;
                break;
            case EAST:
                forgeDirection = ForgeDirection.EAST;
                break;
        }
        return forgeDirection;
    }

    public static AgricultureDirection convertFromForgeDirection(ForgeDirection direction)
    {
        AgricultureDirection agricultureDirection = null;
        switch (direction)
        {
            case SOUTH:
                agricultureDirection = AgricultureDirection.SOUTH;
                break;
            case WEST:
                agricultureDirection = AgricultureDirection.WEST;
                break;
            case NORTH:
                agricultureDirection = AgricultureDirection.NORTH;
                break;
            case EAST:
                agricultureDirection = AgricultureDirection.EAST;
                break;
            default:
                agricultureDirection = null;
        }
        return agricultureDirection;
    }

    public AgricultureDirection getRight()
    {
        return getRight(this);
    }

    public static AgricultureDirection getRight(AgricultureDirection facing)
    {
        switch (facing)
        {
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            default:
                return null;
        }
    }

    public AgricultureDirection getLeft()
    {
        return getLeft(this);
    }

    public static AgricultureDirection getLeft(AgricultureDirection facing)
    {
        switch (facing)
        {
            case SOUTH:
                return EAST;
            case WEST:
                return SOUTH;
            case NORTH:
                return WEST;
            case EAST:
                return NORTH;
            default:
                return null;
        }
    }

    public AgricultureDirection getOpposite()
    {
        return getOpposite(this);
    }

    public static AgricultureDirection getOpposite(AgricultureDirection facing)
    {
        switch (facing)
        {
            case SOUTH:
                return NORTH;
            case WEST:
                return EAST;
            case NORTH:
                return SOUTH;
            case EAST:
                return WEST;
            default:
                return null;
        }
    }

    public int getIndex()
    {
        return directionIndex;
    }

    public static AgricultureDirection getDirectionFromIndex(int index)
    {
        switch (index)
        {
            case 0:
                return SOUTH;
            case 1:
                return WEST;
            case 2:
                return NORTH;
            case 3:
                return EAST;
            default:
                return null;
        }
    }
}
