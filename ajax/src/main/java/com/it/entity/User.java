package com.it.entity;

public class User {

    private Integer id;
    private String name;
    private String address;
    private float score;

    public User() {
    }

    public User(Integer id, String name, String address, float score) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
