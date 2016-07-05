package com.it.test;


import com.google.gson.Gson;
import com.it.entity.User;
import com.sun.javafx.collections.MappingChange;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GsonTestCase {

    @Test
    public void testObject(){
        User user = new User(1,"tom","USA",87.9F);
        //object -> {} 转换成字符串

        String json = new Gson().toJson(user);

        System.out.println(json);
    }

    @Test
    public void testMapobject(){

        Map<String,Object> map = new HashMap<>();
        map.put("id",12236545);
        map.put("m 卡收费卡阿双方的卡上的飞洒的疯狂就发生；jksajessage","今天记得call我");

        //Map -> {}

        String json = new Gson().toJson(map);
        System.out.println(json);
    }

    @Test
    public void testArrayObject(){

        String[] names = {"tom","jim","张三","李四","王五"};
        //arrary -> []

        String json = new Gson().toJson(names);
        System.out.println(json);
    }

    @Test
    public void testObjectArrayToJson(){
        User[] users = new User[3];
        users[0] = new User(1,"张三","河南",80.5F);
        users[1] = new User(2,"李四","山西",60.8F);
        users[2] = new User(3,"JIm","USA",90);

        //Object array -> []
        String json = new Gson().toJson(users);
        System.out.println(json);
    }

    @Test
    public void testListJson(){
        List<User> userList = new ArrayList<>();

        userList.add(new User(1,"张三","China",90.5F));
        userList.add(new User(2,"Lili","USA",80));
        userList.add(new User(3,"Mark","Jpan",60.2F));

        //List ->[] 转换成数组
        String json = new Gson().toJson(userList);
        System.out.println(json);
    }

    @Test
    public void testOtherToJson(){

        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"jim","湖南",80.5F));
        userList.add(new User(2,"tom","陕西",90.8F));
        userList.add(new User(3,"lucy","锦州",55.5F));

        Map<String,Object> map = new HashMap<>();
        map.put("result","success");
        map.put("data",userList);

        //map->{}转换成对象
        String json = new Gson().toJson(map);
        System.out.println(json);
    }
}
