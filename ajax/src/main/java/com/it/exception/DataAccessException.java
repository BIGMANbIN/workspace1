package com.it.exception;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
public class DataAccessException extends RuntimeException{


        public DataAccessException() {}

        public DataAccessException(String msg) {
            super(msg);
        }

        public DataAccessException(String msg,Exception ex) {
            super(msg,ex);
        }

    }
