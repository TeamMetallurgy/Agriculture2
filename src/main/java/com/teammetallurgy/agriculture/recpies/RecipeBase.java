package com.teammetallurgy.agriculture.recpies;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeBase
{
    protected boolean isOreDicMatch(ItemStack base, ItemStack target)
    {

        int[] oreDicIds = OreDictionary.getOreIDs(base);

        for (int i = 0; i < oreDicIds.length; i++)
        {
            String oreDicName = OreDictionary.getOreName(oreDicIds[i]);
            ArrayList<ItemStack> oreDicList = OreDictionary.getOres(oreDicName);

            for (int j = 0; j < oreDicList.size(); j++)
            {
                if (OreDictionary.itemMatches(target, oreDicList.get(j), true)) return true;
            }
        }

        return false;
    }
}
