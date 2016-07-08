package com.it.pojo;

import java.io.Serializable;

public class UserLog implements Serializable {

    private Integer id;
    private String logtime;
    private String logip;
    private Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogtime() {
        return logtime;
    }

    public void setLogtime(String logtime) {
        this.logtime = logtime;
    }

    public String getLogip() {
        return logip;
    }

    public void setLogip(String logip) {
        this.logip = logip;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "UserLog{" +
                "id=" + id +
                ", logtime='" + logtime + '\'' +
                ", logip='" + logip + '\'' +
                ", userid=" + userid +
                '}';
    }
}
