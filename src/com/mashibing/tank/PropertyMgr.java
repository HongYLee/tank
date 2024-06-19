package com.mashibing.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * 功能:
 * 作者：黄焖鸡
 * 日期：2024-06-19 17:52
 */
public class PropertyMgr {
    static Properties props = new Properties();
    static{
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Object get(String key){
        if(props == null){
            return null;
        }
        return props.get(key);
    }

    public static void main(String[] args) {
        System.out.println(PropertyMgr.get("initTankCount"));
    }
}
