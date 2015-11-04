package com.teammetallurgy.agriculture.handler;

import org.apache.logging.log4j.Logger;

public class AgricultureLogHandler
{
    private static Logger LOGGER;
    
    public static void setLogger(Logger log)
    {
        LOGGER = log;
    }
    
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
