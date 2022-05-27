package com.han.ls.project.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class Task {

    private int id;

    private String description;

    private String descImgs;

    // 返回前端页面拼接参数
    private List<String> imgArr;

    private int issuerId;

    private Date publishTime;

    private Date deadline;

    private Date finishTime;

    private int finishUserId;

    private String finishImgs;

    private List<String> finishImgArr;

    private int caseId;

    private String satisfaction;

    private Date createTime;

    private Date updateTime;

    /**
     * 是否可见 1:可见 0:不可见
     */
    private char available;

}
