package com.it.pojo;


import java.io.Serializable;
import java.sql.Timestamp;

public class SalesLog implements Serializable {

    public static final String LOG_TYPE_AUTO = "auto";
    public static final String LOG_TYPE_INPUT = "input";


    private Integer id;
    private Integer logsalesid;
    private String context;
    private Timestamp time;
    private String type;
    private Integer salesid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLogsalesid() {
        return logsalesid;
    }

    public void setLogsalesid(Integer logsalesid) {
        this.logsalesid = logsalesid;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
