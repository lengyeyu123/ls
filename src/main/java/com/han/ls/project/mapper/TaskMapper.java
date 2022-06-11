package com.han.ls.project.mapper;

import com.han.ls.project.domain.Task;
import com.han.ls.project.vo.req.TaskListReqVo;

import java.util.List;

public interface TaskMapper {

    void add(Task setCreateTime);

    List<Task> list(TaskListReqVo reqVo);

    Task selectById(int taskId);

    void finish(Task task);

    void update(Task updateTask);

    void disable(int id);

    List<Task> myTaskList(TaskListReqVo reqVo);

}
