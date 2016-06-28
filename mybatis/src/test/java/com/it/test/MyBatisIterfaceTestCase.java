package com.it.test;


import com.google.common.collect.Maps;
import com.it.mapper.UserMapper;
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

        User user = userMapper.findById(1);

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

        logger.debug("User:{}",user);


        sqlSession.commit();
        sqlSession.close();


    }

    @Test
    public void testUpadate(){
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
        param.put("name","hanhan");
        param.put("password","123123");

        User user = userMapper.findByMap(param);

        logger.debug("{}",user);

        sqlSession.close();
    }
}
