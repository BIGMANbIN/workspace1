package com.it.service;


import com.google.common.collect.Maps;
import com.it.mapper.RoleMapper;
import com.it.mapper.UserLogMapper;
import com.it.mapper.UserMapper;
import com.it.pojo.Role;
import com.it.pojo.User;
import com.it.pojo.UserLog;
import com.it.util.ShiroUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

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
     *
     * @param ip
     */
    public void saveUserLogin(String ip) {
        UserLog userLog = new UserLog();
        userLog.setLoginip(ip);
        userLog.setLogintime(DateTime.now().toString("yyyy-MM-dd HH:mm"));
        userLog.setUserid(ShiroUtil.getCurrentUserID());

        userLogMapper.save(userLog);
    }

    public void changePassword(String password) {
        User user = ShiroUtil.getCurrentUser();
        user.setPassword(DigestUtils.md5Hex(password));

        userMapper.updateUser(user);
    }

    /**
     * 获取当前用户的登录日志
     *
     * @param start
     * @param length
     * @return
     */
    public List<UserLog> findCurrentUserLog(String start, String length) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("userid", ShiroUtil.getCurrentUserID());
        param.put("start", start);
        param.put("length", length);

        return userLogMapper.findByParam(param);
    }


    /**
     * 获取当前登录用户的日志数量
     *
     * @return
     */
    public Long findCurrentUserLogCount() {
        Map<String, Object> param = Maps.newHashMap();
        param.put("userid", ShiroUtil.getCurrentUserID());
        return userLogMapper.countByParam(param);
    }

    /**
     * 根据查询参数获取用户列表
     *
     * @param params
     * @return
     */
    public List<User> findUserListByParam(Map<String, Object> params) {
        return userMapper.findByParam(params);
    }

    /**
     * 获取用户数量
     *
     * @return
     */
    public Long findUserCount() {
        return userMapper.count();
    }

    /**
     * 根据查询条件获取用户数量
     *
     * @param params
     * @return
     */
    public Long findUserCountByParam(Map<String, Object> params) {
        return userMapper.countByParam(params);
    }

    /**
     * 获取所有的角色
     *
     * @return
     */

    public List<Role> findAllRole() {
        return roleMapper.findAll();
    }

    /**
     * 添加新用户
     *
     * @param user
     */
    @Transactional
    public void saveUser(User user) {
        user.setEnable(true);
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));

        //TODO 向微信公众平台注册账号

        userMapper.save(user);
    }

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    public User findUserByUserName(String username) {
        return userMapper.findByUsername(username);
    }

    /**
     * 重置用户密码
     * @param id
     */
    public void resetUserPassword(Integer id) {
        User user = userMapper.findById(id);

        if(user != null){
            user.setPassword(DigestUtils.md5Hex("000000"));
            userMapper.updateUser(user);
        }

    }

    /**
     * 根据用户ID查找用户
     * @param id
     * @return
     */
    public User findByUserId(Integer id) {
        return userMapper.findById(id);
    }

    /**
     * 修改用户信息
     * @param user
     */

    public void editUser(User user) {
        userMapper.updateUser(user);
    }

    /**
     * 显示所有员工
     * @return
     */
    public List<User> findAllUser() {
        return userMapper.findAll();
    }
}
