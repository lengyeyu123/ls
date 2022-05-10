package com.han.ls.project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void add(AddTaskReqVo reqVo) {
        User loginUser = LsUtils.getLoginUser();
        List<String> descImgArr = reqVo.getDescImgArr();
        ObjectMapper objectMapper = new ObjectMapper();
        String descImgs = objectMapper.writeValueAsString(descImgArr);
        Date now = new Date();

        taskMapper.add(new Task().setDescription(reqVo.getDescription())
                .setDescImgs(descImgs)
                .setCreateTime(now)
                .setDeadline(reqVo.getDeadline())
                .setIssuerId(loginUser.getId())
                .setPublishTime(now)
        );

    }

    @SneakyThrows
    public List<Task> list(TaskListReqVo reqVo) {
        PageHelper.startPage(reqVo.getPage(), reqVo.getPageSize());
        List<Task> list = taskMapper.list(reqVo);
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
}
