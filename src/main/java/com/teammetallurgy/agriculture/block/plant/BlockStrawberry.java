package com.teammetallurgy.agriculture.block.plant;

import com.teammetallurgy.agriculture.ItemList;

public class BlockStrawberry extends BlockPlant
{
    public BlockStrawberry()
    {
        this.setBlockTextureName("agriculture:strawberry");
        this.setBlockName("agriculture.strawberry");
        harvestItemStack = ItemList.getItemStack("base", "Strawberry");
    }
}
