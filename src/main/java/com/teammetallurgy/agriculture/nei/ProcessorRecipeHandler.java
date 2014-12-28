package com.teammetallurgy.agriculture.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

import com.teammetallurgy.agriculture.machine.processor.GuiProcessor;
import com.teammetallurgy.agriculture.recpies.RecipeProcessor;
import com.teammetallurgy.agriculture.recpies.Recipes;

public class ProcessorRecipeHandler extends TemplateRecipeHandler
{

    @Override
    public String getRecipeName()
    {
        return NEIClientUtils.translate("recipe.agriculture.processor");
    }

    @Override
    public String getGuiTexture()
    {
        return "agriculture:textures/gui/Processor.png";
    }
    
    @Override
    public String getOverlayIdentifier()
    {
        return "agricultureProcessorRecipe";
    }
    
    @Override
    public void loadTransferRects()
    {
        transferRects.add(new RecipeTransferRect(new Rectangle(84, 23, 24, 18), "agricultureProcessorRecipe"));
    }
    
    @Override
    public Class<? extends GuiContainer> getGuiClass()
    {
        return GuiProcessor.class;
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if (!outputId.equals("agricultureProcessorRecipe") || !(getClass() == ProcessorRecipeHandler.class))
        {
            super.loadCraftingRecipes(outputId, results);
            return;
        }
        
        for (RecipeProcessor pRecipe : Recipes.getProcessorRecipes())
        {
            arecipes.add(new CachedProcessorRecipe(pRecipe.getInput(0), pRecipe.getInput(1), pRecipe.getOutput()));
        }
        
    }
    
    @Override
    public void loadCraftingRecipes(ItemStack result)
    {
        if (result == null || result.getItem() == null)
            return;
        
        for (RecipeProcessor pRecipe : Recipes.getProcessorRecipes())
        {   
            if (pRecipe.getOutput() == null)
                continue;
            
            if(pRecipe.getOutput().isItemEqual(result))
                arecipes.add(new CachedProcessorRecipe(pRecipe.getInput(0), pRecipe.getInput(1), pRecipe.getOutput()));
        }
    }
    
    @Override
    public void loadUsageRecipes(ItemStack ingredient)
    {
        if (ingredient == null || ingredient.getItem() == null)
            return;
        
        for (RecipeProcessor pRecipe : Recipes.getProcessorRecipes())
        {
            if(pRecipe.getInput(0) != null && ingredient.isItemEqual(pRecipe.getInput(0)))
                arecipes.add(new CachedProcessorRecipe(pRecipe.getInput(0), pRecipe.getInput(1), pRecipe.getOutput()));
            
            if(pRecipe.getInput(1) != null && ingredient.isItemEqual(pRecipe.getInput(1)))
                arecipes.add(new CachedProcessorRecipe(pRecipe.getInput(0), pRecipe.getInput(1), pRecipe.getOutput()));
        }
    }
    
    public class CachedProcessorRecipe extends CachedRecipe
    {

        public ArrayList<PositionedStack> ingredinets = new ArrayList<PositionedStack>() ;
        public PositionedStack result;
        
        public CachedProcessorRecipe(ItemStack ingredinet1, ItemStack ingredinet2, ItemStack result)
        {
            this.ingredinets.add(new PositionedStack(ingredinet1, 35, 21));
            if (ingredinet2 != null)
                this.ingredinets.add(new PositionedStack(ingredinet2, 71, 21));
            this.result = new PositionedStack(result, 117, 21);
        }
        
        @Override
        public List<PositionedStack> getIngredients()
        {
            return getCycledIngredients(cycleticks / 20, this.ingredinets);
        }
        
        @Override
        public PositionedStack getResult()
        {
            return result;
        }
        
    }
}
