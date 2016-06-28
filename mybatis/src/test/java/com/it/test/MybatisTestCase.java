package com.it.test;


import com.it.pojo.User;
import com.it.util.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.io.Reader;
import java.util.List;


public class MybatisTestCase {

    private Logger logger = LoggerFactory.getLogger(MybatisTestCase.class);

    @Test
    public void testLoadXml(){

        try {
            //从classpath 读取mybatis.xml文件
            Reader reader = Resources.getResourceAsReader("mybatis.xml");

            //根据sqlSessionFactoryBuilder构建sqlSessionFactor工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

            //根据sqlSessionFactory创建SqlSession对象

            SqlSession sqlSession = sessionFactory.openSession();

            User user = sqlSession.selectOne("com.it.mapper.UserMapper.findById",1);

            logger.debug("{}",user);

            sqlSession.close();

            Assert.assertNotNull(user);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSave(){

        try {
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

            SqlSession sqlSession = sessionFactory.openSession();

            User user = new User();
            user.setName("张飞");
            user.setAddress("韩国");

            sqlSession.insert("com.it.mapper.UserMapper.save",user);
            sqlSession.commit();
            sqlSession.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        User user = sqlSession.selectOne("com.it.mapper.UserMapper.findById",1);

        user.setName("吴镇宇");
        user.setAddress("北京");

        sqlSession.update("com.it.mapper.UserMapper.update",user);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDel(){

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        sqlSession.delete("com.it.mapper.UserMapper.del",3);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testFindAll(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        List<User> userList = sqlSession.selectList("com.it.mapper.UserMapper.findAll");

        for (User user : userList) {
            logger.debug("{}",user);
        }

        sqlSession.close();

        Assert.assertEquals(2, userList.size());
    }

}
