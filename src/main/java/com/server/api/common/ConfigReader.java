package com.server.api.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    /**
     *
     * @param configFilePath config file path
     * @param key config key
     * @return String
     */
    public static String GetValue(String configFilePath,String key)
    {
        InputStream stream;
        String value="";
        try
        {
            stream = new FileInputStream(configFilePath);
            Properties logConifgProperties=new Properties();
            logConifgProperties.load(stream);
            value=logConifgProperties.getProperty(key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println("System.out.println(rootDir);");
        String rootDir=ConfigReader.GetValue("gatConfig.properties","ResourcesDirPath");
        System.out.println(rootDir);
    }
}
