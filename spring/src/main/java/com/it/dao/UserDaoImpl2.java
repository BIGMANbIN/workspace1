package com.it.dao;


public class UserDaoImpl2 implements UserDao{
    @Override
    public void save() {
        System.out.println("Hi Spring");
    }

    @Override
    public Integer save1() {
        System.out.println("user add....");
        return 100;
    }
}


