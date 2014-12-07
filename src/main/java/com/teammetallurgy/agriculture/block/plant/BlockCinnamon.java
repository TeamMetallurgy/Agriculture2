package com.teammetallurgy.agriculture.block.plant;

import com.teammetallurgy.agriculture.ItemList;

public class BlockCinnamon extends BlockPlant
{
    public BlockCinnamon()
    {
        this.setBlockTextureName("agriculture:cinnamon");
        this.setBlockName("agriculture.cinnamon");
        harvestItemStack = ItemList.getItemStack("base", "Cinnamon");
    }
}
