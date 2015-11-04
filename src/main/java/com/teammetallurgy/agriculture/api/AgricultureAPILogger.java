package com.teammetallurgy.agriculture.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AgricultureAPILogger
{
    private static Logger LOGGER = LogManager.getLogger("AgricultureAPI");

    public static void info(String message)
    {
        LOGGER.info(message);
    }
    
    public static void trace(String message)
    {
        LOGGER.trace(message);
    }
    
    public static void warn(String message)
    {
        LOGGER.warn(message);
    }
}
