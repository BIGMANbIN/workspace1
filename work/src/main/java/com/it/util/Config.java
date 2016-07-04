package com.it.util;


import java.io.IOException;
import java.util.Properties;


/**
 * 读取config.properties文件
 *
 *
 */
public class Config {

    private static Properties properties = new Properties();

    static {

        try {
            properties.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("读取文件异常",e);
        }
    }


    public static String get(String key){

        return properties.getProperty(key);
    }

    public static String get(String key,String defaultvlue){

        return properties.getProperty(key,defaultvlue);

    }
}
