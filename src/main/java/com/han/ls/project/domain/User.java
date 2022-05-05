package com.han.ls.project.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private int id;

    private String userName;

    private String password;

    private String phone;

    private String wxCode;

    private String openId;

    private String unionId;

    private String avatarUrl;

    private String remark;

    private int provinceId;

    private int cityId;

    private int countyId;

    private String address;

    private String businessType;

    private int dutyId;

    private Date createTime;

    private Date updateTime;

    private boolean disabledFlag;

}
