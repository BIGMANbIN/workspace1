package com.it.pojo;


import java.io.Serializable;
import java.security.Timestamp;

public class LoginLog implements Serializable{

    private Integer id;
    private Timestamp createtime;
    private String ip;
    private Integer userid;

    public LoginLog() {
    }

    public LoginLog(String ip, Integer userid) {
        this.ip = ip;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
                "id=" + id +
                ", createtime=" + createtime +
                ", ip='" + ip + '\'' +
                ", userid=" + userid +
                '}';
    }
}
