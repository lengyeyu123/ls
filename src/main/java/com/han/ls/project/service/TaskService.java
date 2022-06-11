package com.han.ls.project.service;

import com.github.pagehelper.PageHelper;
import com.han.ls.common.utils.JsonUtils;
import com.han.ls.framework.utils.LsUtils;
import com.han.ls.project.domain.Case;
import com.han.ls.project.domain.Task;
import com.han.ls.project.domain.User;
import com.han.ls.project.mapper.CaseMapper;
import com.han.ls.project.mapper.TaskMapper;
import com.han.ls.project.vo.req.AddTaskReqVo;
import com.han.ls.project.vo.req.FinishTaskReqVo;
import com.han.ls.project.vo.req.TaskListReqVo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private CaseMapper caseMapper;

    @SneakyThrows
    public int add(AddTaskReqVo reqVo) {
        User loginUser = LsUtils.getLoginUser();
        Date now = new Date();

        if (reqVo.getId() != 0) {
            Task updateTask = new Task();
            updateTask.setId(reqVo.getId());
            updateTask.setDescription(reqVo.getDescription());
            updateTask.setDescImgs(JsonUtils.toJson(reqVo.getImgArr()));
            updateTask.setDeadline(reqVo.getDeadline());
            updateTask.setUpdateTime(now);
            taskMapper.update(updateTask);
            return updateTask.getId();
        } else {
            Task task = new Task();
            taskMapper.add(task.setDescription(reqVo.getDescription())
                    .setDescImgs(JsonUtils.toJson(reqVo.getImgArr()))
                    .setCreateTime(now)
                    .setDeadline(reqVo.getDeadline())
                    .setIssuerId(loginUser.getId())
                    .setPublishTime(now)
            );
            return task.getId();
        }

    }

    @SneakyThrows
    public List<Task> list(TaskListReqVo reqVo) {
        PageHelper.startPage(reqVo);
        List<Task> list = taskMapper.list(reqVo);
        for (Task task : list) {
            String descImgs = task.getDescImgs();
            if (StringUtils.isNotBlank(descImgs)) {
                task.setImgArr(JsonUtils.josn2StrList(descImgs));
            }
        }
        return list;
    }

    public List<Task> myTaskList(TaskListReqVo reqVo) {
        reqVo.setUserId(LsUtils.getLoginUser().getId());
        PageHelper.startPage(reqVo);
        List<Task> list = taskMapper.myTaskList(reqVo);
        for (Task task : list) {
            String descImgs = task.getDescImgs();
            if (StringUtils.isNotBlank(descImgs)) {
                task.setImgArr(JsonUtils.josn2StrList(descImgs));
            }
        }
        return list;
    }

    public void finish(FinishTaskReqVo reqVo) {
        User loginUser = LsUtils.getLoginUser();
        Date now = new Date();
        if (reqVo.getFinishTime() == null) {
            reqVo.setFinishTime(now);
        }
        String imgsJson = JsonUtils.toJson(reqVo.getDescImgArr());
        // 1 发布case
        Case lsCase = new Case()
                .setAddress(reqVo.getCaseAddress())
                .setDescription(reqVo.getCaseDesc())
                .setUserId(loginUser.getId())
                .setDescImgs(imgsJson);
        caseMapper.add(lsCase);

        // 2 更新任务完成
        Task task = new Task()
                .setId(reqVo.getTaskId())
                .setFinishTime(reqVo.getFinishTime())
                .setFinishUserId(loginUser.getId())
                .setCaseId(lsCase.getId())
                .setFinishImgs(imgsJson)
                .setUpdateTime(now);
        taskMapper.finish(task);
    }

    public void disable(int id) {
        taskMapper.disable(id);
    }
}
