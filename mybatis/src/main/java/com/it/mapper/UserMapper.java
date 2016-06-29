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


    //1.封装成对象->UserMapper中sql语句->Test中测试

    //(加注解@param("别名")或者用paramXXX表示参数
    User findByParams(@Param("name") String name,@Param("pwd") String password);

    //2.封装成map->Usermapper中sql语句->Test中测试
    User findByMap(Map<String,Object> param);

    List<User> findByPage(@Param("start")String start, @Param("size")String Size);

}
