package com.teammetallurgy.agriculture.block.plant;

import com.teammetallurgy.agriculture.ItemList;

public class BlockPeanut extends BlockPlant
{
    public BlockPeanut()
    {
        this.setBlockTextureName("agriculture:peanut");
        this.setBlockName("agriculture.peanut");
        harvestItemStack = ItemList.getItemStack("base", "Peanuts");
    }

}
