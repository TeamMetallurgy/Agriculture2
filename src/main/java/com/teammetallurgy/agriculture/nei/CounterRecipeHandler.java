package com.teammetallurgy.agriculture.nei;

import java.util.ArrayList;
import java.util.List;

import com.teammetallurgy.agriculture.machine.counter.GuiCounter;
import com.teammetallurgy.agriculture.recpies.RecipeCounter;
import com.teammetallurgy.agriculture.recpies.Recipes;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class CounterRecipeHandler extends TemplateRecipeHandler
{
    @Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if (outputId.equals("agriculture.counter") && getClass() == CounterRecipeHandler.class)
        {
            RecipeCounter[] recipes = Recipes.getCounterRecipes();
            for (RecipeCounter recipe : recipes)
            {
                arecipes.add(new PreparePair(recipe.getIngredients(), recipe.getOutput()));
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
        RecipeCounter[] recipes = Recipes.getCounterRecipes();
        for (RecipeCounter recipe : recipes)
        {
            if (NEIServerUtils.areStacksSameType(recipe.getOutput(), result))
            {
                arecipes.add(new PreparePair(recipe.getIngredients(), recipe.getOutput()));
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient)
    {
        RecipeCounter[] recipes = Recipes.getCounterRecipes();
        for (RecipeCounter recipe : recipes)
        {
            boolean ingredientFound = false;
            for (ArrayList<ItemStack> ingredients : recipe.getIngredients())
            {
                for (ItemStack recipeIngredient : ingredients)
                {
                    if (NEIServerUtils.areStacksSameType(recipeIngredient, ingredient))
                    {
                        PreparePair preparePair = new PreparePair(recipe.getIngredients(), recipe.getOutput());
                        preparePair.setIngredientPermutation(preparePair.ingredients, recipeIngredient);
                        arecipes.add(preparePair);
                        ingredientFound = true;
                        break;
                    }
                }

                if (ingredientFound)
                {
                    break;
                }
            }
        }
    }

    @Override
    public String getRecipeName()
    {
        return NEIClientUtils.translate("recipe.agriculture.counter");
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass()
    {
        return GuiCounter.class;
    }

    @Override
    public String getGuiTexture()
    {
        return "agriculture:textures/gui/CounterNEI.png";
    }

    @Override
    public String getOverlayIdentifier()
    {
        return "agriculture.counter";
    }

    public class PreparePair extends CachedRecipe
    {
        ArrayList<PositionedStack> ingredients = new ArrayList<PositionedStack>();
        PositionedStack result;

        public PreparePair(ArrayList<ArrayList<ItemStack>> ingredientList, ItemStack result)
        {
            for (int i = 0; i < ingredientList.size(); i++)
            {
                ArrayList<ItemStack> ingreditents = ingredientList.get(i);
                this.ingredients.add(new PositionedStack(ingreditents, 30 + (i * 18), 20));
            }

            this.result = new PositionedStack(result, 120, 20);
        }

        @Override
        public List<PositionedStack> getIngredients()
        {
            return getCycledIngredients(cycleticks / 48, ingredients);
        }

        @Override
        public PositionedStack getResult()
        {
            return result;
        }

    }
}
