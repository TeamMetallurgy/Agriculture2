package com.teammetallurgy.agriculture.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
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
        return "agriculture.processor.recipe";
    }

    @Override
    public void loadTransferRects()
    {
        transferRects.add(new RecipeTransferRect(new Rectangle(84, 23, 24, 18), "agriculture.processor.recipe"));
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass()
    {
        return GuiProcessor.class;
    }

    @Override
    public void drawExtras(int recipe)
    {
        drawProgressBar(91, 26, 177, 11, 23, 5, 48, 0);
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if (!outputId.equals("agriculture.processor.recipe") || !(getClass() == ProcessorRecipeHandler.class))
        {
            super.loadCraftingRecipes(outputId, results);
            return;
        }

        for (RecipeProcessor recipe : Recipes.getProcessorRecipes())
        {
            arecipes.add(new CachedProcessorRecipe(recipe.getInputs(0), recipe.getInputs(1), recipe.getOutput()));
        }

    }

    @Override
    public void loadCraftingRecipes(ItemStack result)
    {
        if (result == null || result.getItem() == null) return;

        for (RecipeProcessor recipe : Recipes.getProcessorRecipes())
        {

            if (ItemStack.areItemStacksEqual(recipe.getOutput(), result))
            {
                arecipes.add(new CachedProcessorRecipe(recipe.getInputs(0), recipe.getInputs(1), recipe.getOutput()));
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient)
    {
        if (ingredient == null || ingredient.getItem() == null) return;

        for (RecipeProcessor recipe : Recipes.getProcessorRecipes())
        {
            ItemStack[] ingredients = recipe.getInputs(0);
            for (ItemStack recipeIngredient : ingredients)
            {
                if (OreDictionary.itemMatches(recipeIngredient, ingredient, false))
                {
                    CachedProcessorRecipe cachedRecipe = new CachedProcessorRecipe(recipe.getInputs(0), recipe.getInputs(1), recipe.getOutput());
                    cachedRecipe.setIngredientPermutation(Arrays.asList(cachedRecipe.ingredinets.get(0)), ingredient);
                    arecipes.add(cachedRecipe);
                }
            }

            ingredients = recipe.getInputs(1);
            if (ingredients == null)
            {
                continue;
            }

            for (ItemStack recipeIngredient : ingredients)
            {
                if (OreDictionary.itemMatches(recipeIngredient, ingredient, false))
                {
                    CachedProcessorRecipe cachedRecipe = new CachedProcessorRecipe(recipe.getInputs(0), recipe.getInputs(1), recipe.getOutput());
                    cachedRecipe.setIngredientPermutation(Arrays.asList(cachedRecipe.ingredinets.get(1)), ingredient);
                    arecipes.add(cachedRecipe);
                }
            }
        }
    }

    public class CachedProcessorRecipe extends CachedRecipe
    {

        public ArrayList<PositionedStack> ingredinets = new ArrayList<PositionedStack>();
        public PositionedStack result;

        public CachedProcessorRecipe(ItemStack[] ingredinets1, ItemStack[] ingredinets2, ItemStack result)
        {
            this.ingredinets.add(new PositionedStack(ingredinets1, 35, 21));
            if (ingredinets2 != null) this.ingredinets.add(new PositionedStack(ingredinets2, 71, 21));
            this.result = new PositionedStack(result, 117, 21);
        }

        @Override
        public List<PositionedStack> getIngredients()
        {
            return getCycledIngredients(cycleticks / 48, this.ingredinets);
        }

        @Override
        public PositionedStack getResult()
        {
            return result;
        }

    }
}
