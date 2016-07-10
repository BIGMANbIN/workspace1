package com.it.mapper;


import com.it.pojo.User;
import com.it.pojo.UserLog;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User findByUsername(String username);

    void updateUser(User user);
}
