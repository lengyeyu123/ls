package com.han.ls.project.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 工种标准
 */
@Data
@Accessors(chain = true)
public class DutyStandard {

    private int id;

    private Duty duty;

    private String description;

    private String descImgs;

    private List<String> descImgArr;

}
