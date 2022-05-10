package com.han.ls.project.vo.req;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class CaseListReqVo {

    // 分页
    @Min(1)
    private int page;

    @Min(1)
    private int pageSize;

    private String description;

    private String userName;


}
