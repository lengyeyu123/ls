package com.han.ls.project.vo.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class StandardAddReqVo {

    @Min(1)
    private int duty_id;

    @NotBlank
    private String description;

    private List<String> descImgArr;

}
