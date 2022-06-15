package com.han.ls.project.vo.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CaseListReqVo {

    // 分页
    @NotNull
    @Min(1)
    private Integer pageNum;

    @NotNull
    @Min(1)
    private Integer pageSize;

    private String dutyName;

    private String description;

    private String userName;

    private String searchValue;

    private Integer userId;
}
