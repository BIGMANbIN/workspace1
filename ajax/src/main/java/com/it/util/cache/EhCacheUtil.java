package com.it.util.cache;

import com.it.entity.Message;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;


public class EhCacheUtil {

    private static CacheManager cacheManager = new CacheManager();

    //创建cache
    public static void set(String key, Object value){
        Ehcache ehcache = cacheManager.getEhcache("myCache");
        Element element = new Element(key,value);
        ehcache.put(element);
    }

    //得到cache
    public static Object get(String key) {
        Ehcache ehcache = cacheManager.getEhcache("myCache");
        Element element = ehcache.get(key);

        if (element != null){
            return element.getObjectValue();
        }
        return null;
    }

    //删除cache
    public static void remove(String key){
        Ehcache ehcache = cacheManager.getEhcache("myCache");
        ehcache.remove(key);
    }

}
