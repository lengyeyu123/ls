package com.han.ls.project.vo.req;

import lombok.Data;

@Data
public class LoginReqVo {

    private String code;

    // private String encryptedData;

    // private String iv;

    /**
     * '1' '2' 为测试账户
     */
    private String openId;

}
