package test;


import com.it.pojo.User;
import com.it.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class UserServiceTestCase {

    @Inject
    private UserService userService;

    @Test
    public void testAdd(){
        User user = new User();
        user.setUsername("guangzhou");
        user.setPassword("123456789");
        user.setEmail("hanhan@123.com");

        userService.add(user);
    }


    @Test
    public void testFindById(){
        User user = userService.findUserById(2);
        Assert.assertNotNull(user);
        System.out.println(user);
    }

}
