package com.han.ls.project.controller;


import com.github.pagehelper.PageInfo;
import com.han.ls.framework.web.domain.R;
import com.han.ls.project.service.TaskService;
import com.han.ls.project.vo.req.AddTaskReqVo;
import com.han.ls.project.vo.req.FinishTaskReqVo;
import com.han.ls.project.vo.req.TaskListReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public R add(@RequestBody @Validated AddTaskReqVo reqVo) {
        taskService.add(reqVo);
        return R.success();
    }

    @PostMapping("/list")
    public R<?> list(@RequestBody @Validated TaskListReqVo reqVo) {
        return R.success(new PageInfo<>(taskService.list(reqVo)));
    }

    @PostMapping("/finish")
    public R finish(@RequestBody @Validated FinishTaskReqVo reqVo) {
        taskService.finish(reqVo);
        return R.success();
    }

}
