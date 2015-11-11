package com.teammetallurgy.agriculture.nei;

import java.util.Arrays;

import com.teammetallurgy.agriculture.machine.oven.GuiOven;
import com.teammetallurgy.agriculture.recpies.RecipeOven;
import com.teammetallurgy.agriculture.recpies.Recipes;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class OvenRecipeHandler extends TemplateRecipeHandler
{

    @Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if (outputId.equals("agriculture.oven") && getClass() == OvenRecipeHandler.class)
        {
            RecipeOven[] recipes = Recipes.getOvenRecipes();
            for (RecipeOven recipe : recipes)
            {
                arecipes.add(new BakingPair(recipe.getIngredients(), recipe.getTemp(), recipe.getOutput()));
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
        RecipeOven[] recipes = Recipes.getOvenRecipes();
        for (RecipeOven recipe : recipes)
        {
            if (NEIServerUtils.areStacksSameType(recipe.getOutput(), result))
            {
                arecipes.add(new BakingPair(recipe.getIngredients(), recipe.getTemp(), recipe.getOutput()));
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient)
    {
        RecipeOven[] recipes = Recipes.getOvenRecipes();
        for (RecipeOven recipe : recipes)
        {
            for (ItemStack recipeIngredient : recipe.getIngredients())
            {
                if (NEIServerUtils.areStacksSameTypeCrafting(recipeIngredient, ingredient))
                {
                    BakingPair bakingPair = new BakingPair(recipe.getIngredients(), recipe.getTemp(), recipe.getOutput());
                    bakingPair.setIngredientPermutation(Arrays.asList(bakingPair.ingredient), ingredient);
                    arecipes.add(bakingPair);
                }
            }
        }
    }

    @Override
    public String getRecipeName()
    {
        return NEIClientUtils.translate("recipe.agriculture.oven");
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass()
    {
        return GuiOven.class;
    }

    @Override
    public void drawExtras(int recipe)
    {
        drawProgressBar(74, 23, 179, 88, 15, 15, 48, 7);
    }

    @Override
    public String getGuiTexture()
    {
        return "agriculture:textures/gui/OvenNEI.png";
    }

    @Override
    public String getOverlayIdentifier()
    {
        return "agriculture.oven";
    }

    public class BakingPair extends CachedRecipe
    {
        PositionedStack ingredient;
        PositionedStack result;
        int temp;

        public BakingPair(ItemStack[] ingredient, int temp, ItemStack result)
        {
            this.ingredient = new PositionedStack(ingredient, 51, 23);
            this.temp = temp;
            this.result = new PositionedStack(result, 98, 23);
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
