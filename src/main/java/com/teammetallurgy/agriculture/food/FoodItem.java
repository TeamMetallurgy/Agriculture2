package com.teammetallurgy.agriculture.food;

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
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.teammetallurgy.agriculture.Agriculture;
import com.teammetallurgy.agriculture.food.Food.FoodType;
import com.teammetallurgy.agriculture.food.Food.Methods;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FoodItem extends ItemFood
{
    private HashMap<Integer, String> names = new HashMap<Integer, String>();
    private HashMap<Integer, FoodType> itemTypes = new HashMap<Integer, FoodType>();
    private HashMap<Integer, String> textures = new HashMap<Integer, String>();
    private HashMap<Integer, IIcon> icons = new HashMap<Integer, IIcon>();
    private HashMap<Integer, Integer> healAmounts = new HashMap<Integer, Integer>();
    private HashMap<Integer, Food.Methods> methods = new HashMap<Integer, Food.Methods>();

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

    public void addSubItem(int meta, String name, FoodType itemType, String texture, Methods method)
    {
        this.names.put(meta, name);

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
            unlocalizedName = unlocalizedName.replace(" ", ".").toLowerCase();

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

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advanced)
    {
        list.add(StatCollector.translateToLocal("tooltip.agriculture.heal") + ": " + this.healAmounts.get(itemStack.getItemDamage()));
    }

    public int getHeal(ItemStack itemStack)
    {
        return healAmounts.get(itemStack.getItemDamage());
    }

    public void setHeal(ItemStack stack, int amount)
    {
        healAmounts.put(stack.getItemDamage(), amount);
    }

    // Vanilla Overrides

    // getFoodLevel
    @Override
    public int func_150905_g(ItemStack itemStack)
    {
        return getHeal(itemStack);
    }

    @Override
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (this.itemTypes.get(itemStack.getItemDamage()) != null)
        {
            return super.onEaten(itemStack, world, player);
        }
        return itemStack;
    }

    @Override
    protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (this.itemTypes.get(itemStack.getItemDamage()) != null)
        {
            super.onFoodEaten(itemStack, world, player);
        }
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        FoodType type = itemTypes.get(itemStack.getItemDamage());
        if (type == FoodType.crop || type == FoodType.edible)
        {
            return super.getItemUseAction(itemStack);
        }
        return EnumAction.none;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        FoodType type = itemTypes.get(itemStack.getItemDamage());
        if (type == FoodType.crop || type == FoodType.edible)
        {
            player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        }

        return itemStack;
    }
}
