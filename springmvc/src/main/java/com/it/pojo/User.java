package com.it.pojo;


public class User {

    private String username;
    private String address;
    private Integer age;
    public String comment;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String commet) {
        this.comment = commet;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", age='" + age + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
