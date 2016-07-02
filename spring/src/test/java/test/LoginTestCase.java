package test;

import com.it.dao.LoginDao;
import com.it.pojo.LoginLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class LoginTestCase {

    @Inject
    private LoginDao loginDao;

    @Test
    public void testLogin() {

        LoginLog loginLog = new LoginLog();
        loginLog.setIp("28.1.1.251");
        loginLog.setUserid(8);

        loginDao.save(loginLog);
    }
}
