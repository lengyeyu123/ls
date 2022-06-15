package com.han.ls.project.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AddCaseReqVo {

    private int id;

    @NotBlank
    private String description;

    private int countyId;

    private String address;

    @NotNull
    private List<String> imgArr;

    private List<Integer> dutyIdArr;

}
