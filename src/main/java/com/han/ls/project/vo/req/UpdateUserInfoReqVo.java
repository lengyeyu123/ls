package com.han.ls.project.vo.req;

import lombok.Data;

@Data
public class UpdateUserInfoReqVo {

    private String phone;

    private String wxCode;

    private String address;

    private int countyId;

    private int dutyId;

}