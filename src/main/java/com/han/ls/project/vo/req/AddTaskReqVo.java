package com.han.ls.project.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class AddTaskReqVo {

    private int id;

    /**
     * 描述
     */
    @NotBlank
    private String description;

    /**
     * 描述图片
     */
    @NotNull
    private List<String> imgArr;

    /**
     * 截止时间
     */
    @NotNull
    private Date deadline;

    @NotNull
    private List<Integer> dutyIdArr;

}
