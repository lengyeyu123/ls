package com.han.ls.project.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * 工种 水电油木泥
 */
@Data
@Accessors(chain = true)
public class Duty {

    private int id;

    @NotBlank
    private String name;

    private String description;

    /**
     * 排序字段
     */
    @Min(1)
    private int orderNo;

}
