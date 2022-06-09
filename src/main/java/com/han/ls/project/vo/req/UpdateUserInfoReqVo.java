package com.han.ls.project.vo.req;

import lombok.Data;

@Data
public class UpdateUserInfoReqVo {

    private String userName;

    private String avatarUrl;

    private String phone;

    private String wxCode;

    private String address;

    private int countyId;

    private String countyName;

    private int dutyId;

}
