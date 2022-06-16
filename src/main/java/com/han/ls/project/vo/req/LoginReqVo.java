package com.han.ls.project.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginReqVo {

    @NotBlank
    private String code;

}
