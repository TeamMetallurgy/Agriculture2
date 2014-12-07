package com.teammetallurgy.agriculture.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class AgricultureConfigHandler
{
    private static Configuration configuration;

    public static boolean generates(String name)
    {
        boolean b = configuration.get("Generators", name, true).getBoolean(true);

        saveChanges();

        return b;
    }

    private static boolean getBoolean(String categories, String key, boolean defaultValue)
    {
        boolean b = configuration.get(categories, key, defaultValue).getBoolean(defaultValue);

        saveChanges();

        return b;
    }

    @Deprecated
    public static int getItem(String itemName, int defaultid)
    {
        return defaultid;
    }

    @Deprecated
    public static int getItem(String category, String itemName, Integer defaultid)
    {
        return defaultid;
    }

    private static String getName(String categories, String key, String defaultValue)
    {
        String string = configuration.get(categories, key, defaultValue).toString();

        saveChanges();

        return string;
    }

    public static boolean itemEnabled(String itemName)
    {
        boolean b = configuration.get("Items", itemName, true).getBoolean(true);

        saveChanges();
        return b;
    }

    public static boolean regen()
    {
        return getBoolean("World_Regen", "regen", false);
    }

    public static String regenKey()
    {
        return getName("World_Regen", "regen_key", "DEFAULT");
    }

    private static void saveChanges()
    {

        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }

    public static boolean setEnabled(String setName)
    {
        boolean b = configuration.get("Sets", setName, true).getBoolean(true);

        saveChanges();

        return b;
    }

    public static void setFile(File file)
    {
        configuration = new Configuration(file);

        configuration.load();

        saveChanges();
    }
}
