package com.it.service;


import com.it.mapper.TaskMapper;

import com.it.pojo.Task;
import com.it.util.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@Named
public class TaskService {

    @Inject
    private TaskMapper taskMapper;

    private Logger logger = LoggerFactory.getLogger(TaskService.class);

    /**
     * 新建待办事项
     * @param task
     * @param hour
     * @param min
     */
    public void saveTask(Task task, String hour, String min) {
        if(StringUtils.isNotEmpty(hour) && StringUtils.isNotEmpty(min)){
            String reminderTime = task.getStart()+" "+hour+":"+min+":00";
            logger.debug("提醒时间为{}",reminderTime);
            task.setRemindertime(reminderTime);
        }
        task.setUserid(ShiroUtil.getCurrentUserID());
        taskMapper.save(task);
    }

    /**
     * 获取当前用户的所有任务
     * @param start
     * @param end
     * @return
     */
    public List<Task> findTaskByUserId(String start, String end) {
        return taskMapper.findByUserIdAAndDateRanger(ShiroUtil.getCurrentUserID(),start,end);
    }

    /**
     * 删除日程
     * @param id
     */
    public void delTask(Integer id) {
        taskMapper.del(id);
    }

    /**
     * 获取当前用户已超时的任务
     * @return
     */
    public List<Task> findTimeOutTasks() {
        String today = DateTime.now().toString("YYYY-MM-dd");
        return taskMapper.findTimeOutTask(ShiroUtil.getCurrentUserID(),today);
    }

    /**
     * 将日程设置为已完成
     * @param id
     * @return
     */
    public Task doneTask(Integer id) {
        Task task = taskMapper.findById(id);
        task.setDone(true);
        task.setColor("#cccccc");
        taskMapper.update(task);
        return task;

    }
}
