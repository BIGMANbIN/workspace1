package com.it.test;


import com.it.dao.MessageDao;
import com.it.entity.Message;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;

public class MessageDaoTestCase {

    private MessageDao messageDao = new MessageDao();

    @Test
    public void testFindById(){

        Message message = messageDao.findById(1);
        Assert.assertNotNull(message);

    }


    @Test
    public void testOne(){

        System.out.println(System.getProperty("java.io.tmpdir"));
    }

    public void testEhCache(){

        CacheManager cacheManager = new CacheManager();

        Ehcache ehcache = cacheManager.getEhcache("myCache");


        //向缓存中存值

        Element element = new Element("user:1","java");
        ehcache.put(element);
    }
}

