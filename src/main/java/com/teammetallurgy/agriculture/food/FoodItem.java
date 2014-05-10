package com.teammetallurgy.agriculture.food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.teammetallurgy.agriculture.Agriculture;
import com.teammetallurgy.agriculture.food.Food.Methods;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FoodItem extends ItemFood
{
    private final HashMap<Integer, String> names = new HashMap<Integer, String>();
    private final HashMap<Integer, Integer> itemTypes = new HashMap<Integer, Integer>();
    private final HashMap<Integer, String> textures = new HashMap<Integer, String>();
    private final HashMap<Integer, IIcon> icons = new HashMap<Integer, IIcon>();
    private final HashMap<Integer, ArrayList<ItemStack>> recipes = new HashMap<Integer, ArrayList<ItemStack>>();
    private final HashMap<Integer, Integer> healAmounts = new HashMap<Integer, Integer>();
    private final HashMap<Integer, Food.Methods> methods = new HashMap<Integer, Food.Methods>();

    public FoodItem(String postfix, int health)
    {
        super(health, false);
        this.healAmounts.put(0, 2);
        this.setTextureName(Agriculture.MODID + ":food_item_default");
        this.setUnlocalizedName("food.item." + postfix);
        this.setCreativeTab(Agriculture.instance.creativeTabFood);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    public void addSubItem(int meta, String name, int itemType, String texture, Methods method)
    {
        this.names.put(meta, name);

        if ((itemType < 0) || (itemType > 2))
        {
            itemType = 0;
        }

        this.itemTypes.put(meta, itemType);

        this.textures.put(meta, texture);
        this.methods.put(meta, method);
        this.healAmounts.put(meta, 2);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int meta)
    {
        if (this.icons.containsKey(meta))
        {
            return this.icons.get(meta);
        }
        else
        {
            return this.itemIcon;
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        for (Map.Entry<Integer, String> name : this.names.entrySet())
        {
            list.add(new ItemStack(item, 1, name.getKey()));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        int meta = itemStack.getItemDamage();

        if (this.names.get(meta) != null)
        {
            String unlocalizedName = this.names.get(meta);
            unlocalizedName = unlocalizedName.replace(" ", "").toLowerCase();

            String prefix = "item." + Agriculture.MODID.toLowerCase() + ".";
            return prefix + unlocalizedName;
        }
        else
        {
            return this.getUnlocalizedName();
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister register)
    {
        // Default texture
        this.itemIcon = register.registerIcon(this.getIconString());

        // Sub-Items textures
        for (Map.Entry<Integer, String> texture : this.textures.entrySet())
        {
            int meta = texture.getKey();
            String textureName = texture.getValue();

            IIcon icon = register.registerIcon(textureName);

            this.icons.put(meta, icon);
        }
    }

    public void updateRecipe(int itemDamage, ArrayList<ItemStack> itemStacks)
    {
        this.recipes.put(itemDamage, itemStacks);
    }

    public void calculateValues(int itemDamage)
    {
        ArrayList<ItemStack> arrayList = this.recipes.get(itemDamage);

        int healAmount = 0;

        healAmount = this.calculateItemValue(itemDamage, arrayList, healAmount);

        this.healAmounts.put(itemDamage, healAmount);
    }

    private int calculateItemValue(int itemDamage, ArrayList<ItemStack> arrayList, int healAmount)
    {
        for (ItemStack stack : arrayList)
        {
            Item item = stack.getItem();
            if ((item != null))
            {
                ArrayList<ItemStack> arrayList2 = this.recipes.get(stack.getItemDamage());
                if ((item instanceof FoodItem) && (arrayList2 != null))
                {
                    healAmount += this.calculateItemValue(stack.getItemDamage(), arrayList2, healAmount);
                }
                else if (item instanceof ItemFood)
                {
                    healAmount += ((ItemFood) item).func_150905_g(stack);
                }

                else
                {
                    healAmount += 2;
                }
            }
            if (this.methods.get(itemDamage) == Methods.bake)
            {
                healAmount += 3;
            }

            if (this.methods.get(itemDamage) == Methods.process)
            {
                healAmount += 2;
            }
        }
        return healAmount;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advanced)
    {
        list.add("Heal amount: " + this.healAmounts.get(itemStack.getItemDamage()));
    }

    // Vanilla Overrides

    @Override
    public int func_150905_g(ItemStack itemStack)
    {
        return this.healAmounts.get(itemStack.getItemDamage());
    }

    @Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (this.itemTypes.get(itemStack.getItemDamage()) == null) { return super.onEaten(itemStack, world, player); }
        return itemStack;
    }

    @Override
    protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (this.itemTypes.get(itemStack.getItemDamage()) == null)
        {
            super.onFoodEaten(itemStack, world, player);
        }
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        if (this.itemTypes.get(itemStack.getItemDamage()) == 1) { return super.getItemUseAction(itemStack); }
        return EnumAction.none;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (this.itemTypes.get(itemStack.getItemDamage()) == 1)
        {
            player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        }

        return itemStack;
    }
}
