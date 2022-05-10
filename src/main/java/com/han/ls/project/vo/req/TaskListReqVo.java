package com.han.ls.project.vo.req;

import lombok.Data;

import java.util.Date;

@Data
public class TaskListReqVo {

    private String description;

    private int dutyId;

    private String userName;

    private Date publishTimeStart;

    private Date publishTimeEnd;

    // 分页
    private int page = 1;

    private int pageSize = 10;

}
