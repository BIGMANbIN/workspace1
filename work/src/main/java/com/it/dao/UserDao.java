package com.it.dao;


import com.it.entity.User;
import com.it.util.DbHelp;

import java.util.List;

public class UserDao {

    public void add(User user) {
        String sql = "insert into td_user(username,password,address,email)";
        DbHelp.update(sql,user.getUsername(),user.getPassword(),user.getAddress(),user.getEmail());
    }

    public void del(Integer id){

    }

    public void update(User user){

    }

    public User findById(Integer id){
        return null;
    }

    public List<User> findAll(){
        return null;
    }

    public User findByUsername(){
        return null;
    }

}
