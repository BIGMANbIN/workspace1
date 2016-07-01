package test;


import com.it.dao.UserDao;
import com.it.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class UserDaoTestCase {

    @Inject
    private UserDao userDao;

    @Test
    public void testAdd() {
        User user = new User();
        user.setUsername("张飞");
        user.setPassword("123456789");
        user.setEmail("123123@163.com");

        userDao.add(user);
    }

    @Test
    public void testDel() {
        userDao.del(4);

    }

    @Test
    public void testUpdate() {
        User user = userDao.findById(5);
        user.setUsername("张三");
        user.setPassword("123123123");

        userDao.update(user);

    }

    @Test
    public void testFindById() {

        User user = userDao.findById(5);
        Assert.assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void testFindAll() {
        List<User> userList = userDao.findAll();
        Assert.assertNotNull(userList);
        System.out.println(userList);

    }

    @Test
    public void testFindByName() {

        User user = userDao.findByName("张三");
        Assert.assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void testCount() {
        Long count = userDao.count();
        Assert.assertEquals(count.intValue(), 4);
    }
}
