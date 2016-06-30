package com.it.dao;


public class UserDaoImpl1 implements UserDao{


    @Override
    public void save() {
        System.out.println("Hello Spring");
    }

    @Override
    public Integer save1() {
        System.out.println("save1....");
        if(1==1) {
            throw new RuntimeException("出大事了....");
        }
        return 10000;
    }
}
