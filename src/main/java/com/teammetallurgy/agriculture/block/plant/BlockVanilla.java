package com.teammetallurgy.agriculture.block.plant;

import com.teammetallurgy.agriculture.ItemList;

public class BlockVanilla extends BlockPlant
{
    public BlockVanilla()
    {
        this.setBlockTextureName("agriculture:vanilla");
        this.setBlockName("agriculture.vanilla");
        harvestItemStack = ItemList.getItemStack("base", "Vanilla");
    }
}
