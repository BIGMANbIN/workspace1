package com.it.service;


import com.it.mapper.RoleMapper;
import com.it.mapper.UserMapper;
import com.it.pojo.Role;
import com.it.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ShiroRealm extends AuthorizingRealm {

    @Inject
    private UserMapper userMapper;

    @Inject
    private RoleMapper roleMapper;


    /**
     * 验证用户是否具有某项权限
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User user = (User) principalCollection.getPrimaryPrincipal();
        if (user != null) {
            //根据用户的RoleID获取role对象
            Integer roleId = user.getRoleid();
            Role role = roleMapper.findById(roleId);

            //将用户的角色名称赋值给info

            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addRole(role.getRolename());

            return info;
        }
        return null;
    }


    /**
     * 验证用户账号和密码是否正确
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        String username = token.getUsername();//获取用户表单中的账号
        User user = userMapper.findByUsername(username);//根据账号查找相应的对象

        if (user != null) {
            if (!user.getEnable()) {
                throw new LockedAccountException("账号已被禁用");
            }
            return new SimpleAuthenticationInfo(user, user.getPassowrd(), getName());

        } else {
            throw new UnknownAccountException("账号或者密码错误");
        }

    }


}
