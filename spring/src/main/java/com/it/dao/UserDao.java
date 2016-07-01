package com.it.dao;


import com.it.pojo.User;

import java.util.List;

public interface UserDao {

    void add(User user);
    void del(Integer id);
    void update(User user);
    User findById(Integer id);
    List<User> findAll();
    User findByName(String username);

    Long count();

}
