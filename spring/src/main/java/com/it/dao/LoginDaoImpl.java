package com.it.dao;


import com.it.pojo.LoginLog;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;
import javax.inject.Named;


@Named
public class LoginDaoImpl implements LoginDao {


    @Inject
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(LoginLog loginLog) {
        String sql = "insert into t_log(ip,userid) values (?,?)";
        jdbcTemplate.update(sql, loginLog.getIp(), loginLog.getUserid());
    }
}
