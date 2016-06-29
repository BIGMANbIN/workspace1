package com.it.test;


import com.google.common.collect.Maps;
import com.it.mapper.TopicMapper;
import com.it.mapper.UserMapper;
import com.it.pojo.Tag;
import com.it.pojo.User;
import com.it.util.MyBatisUtil;
import junit.framework.Assert;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisIterfaceTestCase {

    private Logger logger = LoggerFactory.getLogger(MybatisTestCase.class);


    @Test
    public void testFindById(){

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.findById(10);

        List<Tag> tagList = user.getTagList();
        logger.debug("Tag:{}", tagList);

        sqlSession.close();
        Assert.assertNotNull(user);

    }

    @Test
    public void testSave(){

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setName("MeiMei");
        user.setAddress("China");

        userMapper.save(user);

        logger.debug("UserID:{}",user.getId());


        sqlSession.commit();
        sqlSession.close();


    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.findById(4);

        user.setName("Lili");

        userMapper.update(user);

        logger.debug("User:{}",user);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDel(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        userMapper.del(5);

        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void testFindAll(){

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = userMapper.findAll();

        for (User user : userList) {
            logger.debug("User:{}",user);

        }
        sqlSession.close();
    }

    @Test
    public void testFindByMap(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Map<String,Object> param = Maps.newHashMap();
        param.put("name","jim");
        param.put("password","123123");

        User user = userMapper.findByMap(param);

        logger.debug("{}",user);

        sqlSession.close();
    }

    @Test
    public void  testFindByParams(){

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.findByParams("jim","123123");

        logger.debug("user:{}",user);

        sqlSession.close();

    }

    @Test
    public void testFindByQueryParam(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Map<String,Object> queryParam = Maps.newHashMap();

        //queryParam.put("username","tom");
        queryParam.put("password","123456");
        queryParam.put("email","123@123.com");

        userMapper.findByQueryParam(queryParam);

        sqlSession.close();
    }

    @Test
    public void testFindByPage(){

        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = userMapper.findByPage("0","15");

        for (User user : userList) {
            logger.debug("{}",user);
        }

        sqlSession.close();

    }


}
