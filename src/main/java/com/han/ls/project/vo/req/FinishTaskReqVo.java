package com.han.ls.project.vo.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class FinishTaskReqVo {

    @NotNull
    @Min(1)
    private Integer taskId;

    private Date finishTime;

    /**
     * 完成任务展示图片地址arr 同时发布case
     */
    @NotNull
    private List<String> descImgArr;

    @NotBlank
    private String caseDesc;

    private String caseAddress;

}
