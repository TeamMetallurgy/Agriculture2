package com.teammetallurgy.agriculture.nei;

import java.util.Arrays;

import com.teammetallurgy.agriculture.machine.icebox.GuiIcebox;
import com.teammetallurgy.agriculture.recpies.RecipeIceBox;
import com.teammetallurgy.agriculture.recpies.Recipes;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class IceBoxRecipeHandler extends TemplateRecipeHandler
{
    @Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if(outputId.equals("agriculture.icebox") && getClass() == IceBoxRecipeHandler.class)
        {
            RecipeIceBox[] recipes = Recipes.getIceboxRecipes();
            for(RecipeIceBox recipe : recipes)
            {
                arecipes.add(new FreezingPair(recipe.getIngredients(), recipe.getTemp(), recipe.getOutput()));
            }
        }
        else
        {
            super.loadCraftingRecipes(outputId, results);
        }
    }
    
    @Override
    public void loadCraftingRecipes(ItemStack result)
    {
        RecipeIceBox[] recipes = Recipes.getIceboxRecipes();
        for(RecipeIceBox recipe : recipes)
        {
            if (NEIServerUtils.areStacksSameType(recipe.getOutput(), result))
            {
                arecipes.add(new FreezingPair(recipe.getIngredients(), recipe.getTemp(), recipe.getOutput()));
            }
        }
    }
    
    @Override
    public void loadUsageRecipes(ItemStack ingredient)
    {
        RecipeIceBox[] recipes = Recipes.getIceboxRecipes();
        for(RecipeIceBox recipe : recipes)
        {
            for (ItemStack recipeIngredient : recipe.getIngredients())
            {
                if (NEIServerUtils.areStacksSameType(recipeIngredient, ingredient))
                {
                    FreezingPair freezingPair = new FreezingPair(recipe.getIngredients(), recipe.getTemp(), recipe.getOutput());
                    freezingPair.setIngredientPermutation(Arrays.asList(freezingPair.ingredient), ingredient);
                    arecipes.add(freezingPair);
                    break;
                }
            }
        }
    }

    @Override
    public String getRecipeName()
    {
        return NEIClientUtils.translate("recipe.agriculture.icebox");
    }

    @Override
    public String getGuiTexture()
    {
        return "agriculture:textures/gui/IceboxNEI.png";
    }
    
    @Override
    public void drawExtras(int recipe)
    {
        drawProgressBar(71, 27, 179, 11, 25, 7, 48, 0);
    }
    
    @Override
    public Class<? extends GuiContainer> getGuiClass()
    {
        return GuiIcebox.class;
    }
    
    @Override
    public String getOverlayIdentifier()
    {     
        return "agriculture.icebox";
    }
    
    public class FreezingPair extends CachedRecipe
    {

        PositionedStack ingredient;
        PositionedStack result;
        int temp;
        
        public FreezingPair(ItemStack[] ingredient, int temp, ItemStack result)
        {
            this.ingredient = new PositionedStack(ingredient, 48, 22);
            this.temp = temp;
            this.result = new PositionedStack(result, 101, 22);
        }
        
        @Override
        public PositionedStack getIngredient()
        {
            return ingredient;
        }
        
        @Override
        public PositionedStack getResult()
        {
            return result;
        }
        
    }

}
