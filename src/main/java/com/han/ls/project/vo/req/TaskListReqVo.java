package com.han.ls.project.vo.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TaskListReqVo {

    private String description;

    private int dutyId;

    private String userName;

    private Date publishTimeStart;

    private Date publishTimeEnd;

    private String searchValue;

    private int userId;

    // 分页
    @NotNull
    @Min(1)
    private Integer pageNum;

    @NotNull
    @Min(1)
    private Integer pageSize;

}
