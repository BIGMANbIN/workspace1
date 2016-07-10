package com.it.service;


import com.it.mapper.RoleMapper;
import com.it.mapper.UserLogMapper;
import com.it.mapper.UserMapper;
import com.it.pojo.UserLog;
import com.it.util.ShiroUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Inject
    private UserMapper userMapper;

    @Inject
    private UserLogMapper userLogMapper;

    @Inject
    private RoleMapper roleMapper;

    /**
     * 创建用户的登录日志
     * @param ip
     */
    public void saveUserLogin(String ip) {
        UserLog userLog = new UserLog();
        userLog.setLoginip(ip);
        userLog.setLogintime(DateTime.now().toString("yyyy-MM-dd HH:mm"));
        userLog.setUserid(ShiroUtil.getCurrentUserID());

        userLogMapper.save(userLog);
    }
}
