package com.it.mapper;


import com.it.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User findById(Integer id);

    void save(User user);

    void update(User user);

    void del(Integer id);

    List<User> findAll();

    List<User> findByQueryParam(Map<String,Object> queryParam);

    User findByParam(@Param("name") String name,@Param("pwd") String password);

    User findByMap(Map<String,Object> param);
}
