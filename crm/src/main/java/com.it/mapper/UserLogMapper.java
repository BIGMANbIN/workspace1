package com.it.mapper;


import com.it.pojo.UserLog;
import java.util.Map;

import java.util.List;

public interface UserLogMapper {
    void save(UserLog userLog);

    List<UserLog> findByParam(Map<String,Object> param);

    Long countByParam(Map<String, Object> param);
}
