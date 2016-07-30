package com.bin.it;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

public class JedisStringTestCase {

    private Jedis jedis = null;

    @Before
    public void setUp() {
        jedis = new Jedis("192.168.1.38");
    }

    @After
    public void close() {
        if (jedis != null) {
            jedis.close();
        }
    }

    @Test
    public void testSet() {
        jedis.set("jedis", "version-2");
    }

    @Test
    public void testGet() {
        String value = jedis.get("jedis");
        Assert.assertEquals("version-2", value);
    }

    @Test
    public void testExists(){
        if(!jedis.exists("jedis")){
            jedis.set("jedis","version-3");
        }
    }

    @Test
    public void tessIncr(){
        String key = "name:1:view";
        jedis.incr(key);

        System.out.println(jedis.get("name:1:view"));
    }

    @Test
    public void testIncrBy(){
        String key = "name:1:view";
        jedis.incrBy(key,200);
        System.out.println(jedis.get("name:1:view"));
    }

    @Test
    public void testIncrFloat(){
        String key = "name:1:view";
        jedis.incrByFloat(key,0.8F);
        System.out.println(jedis.get("name:1:view"));
    }

    @Test
    public void testDecr(){
        String key = "name:1:view";
        jedis.decrBy(key,50);
        System.out.println(jedis.get("name:1:view"));
    }

    @Test
    public void testOppend(){
        jedis.append("jedis","jim");
        System.out.println(jedis.get("jedis"));
    }

    @Test
    public void testStrlen(){
        System.out.println(jedis.strlen("jedis"));
    }

    @Test
    public void testMet(){
        jedis.mset("k5","v5","k6","v6");
    }

    @Test
    public void testMget(){
        List<String> stringList = jedis.mget("k5","k6");
        System.out.println(stringList);
    }
}


