package com.teammetallurgy.agriculture.nei;

import java.util.Arrays;

import com.teammetallurgy.agriculture.machine.frier.GuiFrier;
import com.teammetallurgy.agriculture.recpies.RecipeFrier;
import com.teammetallurgy.agriculture.recpies.Recipes;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class FrierRecipeHandler extends TemplateRecipeHandler
{

    @Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if (outputId.equals("agriculture.frier") && getClass() == FrierRecipeHandler.class)
        {
            RecipeFrier[] recipes = Recipes.getFrierRecipes();
            for (RecipeFrier recipe : recipes)
            {
                arecipes.add(new FryingPair(recipe.getIngredients(), recipe.getTemp(), recipe.getOutput()));
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
        RecipeFrier[] recipes = Recipes.getFrierRecipes();
        for (RecipeFrier recipe : recipes)
        {
            if (NEIServerUtils.areStacksSameType(recipe.getOutput(), result))
            {
                arecipes.add(new FryingPair(recipe.getIngredients(), recipe.getTemp(), recipe.getOutput()));
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient)
    {
        RecipeFrier[] recipes = Recipes.getFrierRecipes();
        for (RecipeFrier recipe : recipes)
        {
            for (ItemStack recipeIngredient : recipe.getIngredients())
            {
                if (NEIServerUtils.areStacksSameTypeCrafting(recipeIngredient, ingredient))
                {
                    FryingPair fryingPair = new FryingPair(recipe.getIngredients(), recipe.getTemp(), recipe.getOutput());
                    fryingPair.setIngredientPermutation(Arrays.asList(fryingPair.ingredients), ingredient);
                    arecipes.add(fryingPair);
                    break;
                }
            }
        }
    }

    @Override
    public String getRecipeName()
    {
        return NEIClientUtils.translate("recipe.agriculture.frier");
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass()
    {
        return GuiFrier.class;
    }

    @Override
    public void drawExtras(int recipe)
    {
        drawProgressBar(100, 25, 179, 72, 14, 14, 48, 7);
    }

    @Override
    public String getGuiTexture()
    {
        return "agriculture:textures/gui/FrierNEI.png";
    }

    @Override
    public String getOverlayIdentifier()
    {
        return "agriculture.frier";
    }

    public class FryingPair extends CachedRecipe
    {
        PositionedStack ingredients;
        int temp;
        PositionedStack result;

        public FryingPair(ItemStack[] ingredients, int temp, ItemStack result)
        {
            this.ingredients = new PositionedStack(ingredients, 82, 24);
            this.temp = temp;
            this.result = new PositionedStack(result, 118, 24);
        }

        @Override
        public PositionedStack getIngredient()
        {
            return ingredients;
        }

        public int getTemp()
        {
            return temp;
        }

        @Override
        public PositionedStack getResult()
        {
            return result;
        }
    }
}
