package com.it.util;

import com.it.exception.DataAccessException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import java.sql.SQLException;


public class DbHelp {

    public static void update(String sql,Object... params) {
            QueryRunner queryRunner = new QueryRunner(ConnectionManager.getDataSource());

        try {
                queryRunner.update(sql,params);
            } catch (SQLException e) {
                throw new DataAccessException("执行:" + sql + "异常",e);
            }
        }

        public static <T> T query(String sql, ResultSetHandler<T> handler,Object... params) {
            QueryRunner queryRunner = new QueryRunner(ConnectionManager.getDataSource());
            try {
                return queryRunner.query(sql,handler,params);
            } catch (SQLException e) {
                throw new DataAccessException("执行:" + sql + "异常",e);
            }
        }

    }
