package com.it.mapper;


import com.it.pojo.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapper {
    void save(Task task);

    List<Task> findByUserIdAAndDateRanger(@Param("userId") Integer currentUserID, @Param("start") String start, @Param("end") String end);

    void del(Integer id);

    List<Task> findTimeOutTask(@Param("userId") Integer currentUserID, @Param("today") String today);

    Task findById(Integer id);

    void update(Task task);

    List<Task> findByUserId(Integer userId);
}
