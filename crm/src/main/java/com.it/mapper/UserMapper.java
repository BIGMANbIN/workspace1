package com.it.mapper;


import com.it.pojo.User;
import com.it.pojo.UserLog;

public interface UserMapper {

    User findByUsername(String username);
}
