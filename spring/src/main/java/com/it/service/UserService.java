package com.it.service;


import com.it.dao.UserDao;

public class UserService {

    private UserDao userDao;

    public UserService (UserDao user){
        this.userDao = user;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void sayHi(){
        System.out.println("Hi java!");
    }

    public void sayHello(){
        System.out.println("Hello,java!");
    }
}
