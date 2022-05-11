package com.han.ls.project.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AddCaseReqVo {

    @NotBlank
    private String description;

    private Integer countyId;

    private String address;

    @NotNull
    private List<String> descImgArr;

}
