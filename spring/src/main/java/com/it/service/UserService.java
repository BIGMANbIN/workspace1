package com.it.service;


import com.it.mapper.UserMapper;
import com.it.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Transactional
public class UserService {

    @Inject
    private UserMapper userMapper;

    public void add(User user) {
        userMapper.add(user);
    }

    public User findUserById(Integer id) {
        return userMapper.findById(id);

    }


//业务层
//1.验证登陆
//2.记录登陆的时间和ip
//3.给这个账号发邮件
   /* @Inject
    private UserDao userDao;

    @Inject
    private LoginDao loginDao;


    // 0. 事务添加在Service层
    // 1. 碰到RuntimeException事务会回滚
    // 2. @Transactional(rollbackFor = Exception.class) 这种将碰到所有的异常，事务都会回滚
    // 3. @Transactional(noRollbackFor = RuntimeException.class) 碰到RuntimeException将不回滚
    // 4. @Transactional(readOnly = true) 推荐查询方法配置为只读事务，性能较高
    // 5. @Transactional可以添加在类级别或方法级别，如果添加在类级别，则所有方法都将加入事务
    // 6. @Transactional(isolation = Isolation.SERIALIZABLE) 修改事务的隔离级别
    // 7. @Transactional(propagation = Propagation.REQUIRED) 修改事务的传播属性



    public User login(String username, String password, String ip) {
        User user = userDao.findByName(username);

        if (user != null && user.getPassword().equals(password)){
            loginDao.save(new LoginLog(ip,user.getId()));

            EmailUtil.sendEmail();
            return user;
        }else{
            throw new RuntimeException("用户名或密码错误！");
        }
    }
*/
}
