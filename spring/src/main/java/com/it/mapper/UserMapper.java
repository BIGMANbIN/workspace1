package com.it.mapper;


import com.it.pojo.User;

public interface UserMapper {

    void add(User user);

    User findById(Integer id);
}
