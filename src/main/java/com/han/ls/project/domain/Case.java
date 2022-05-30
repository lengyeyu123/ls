package com.han.ls.project.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Case {

    private int id;

    private int userId;

    private String description;

    private int countyId;

    private String address;

    private String descImgs;

    // 前端返回拼接字段
    private List<String> imgArr;

    /**
     * 收藏标志
     */
    private boolean collectFlag;

    /**
     * 是否可见 1:可见 0:不可见
     */
    private char available;

}
