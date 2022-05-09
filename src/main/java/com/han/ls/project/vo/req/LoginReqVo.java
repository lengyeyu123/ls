package com.han.ls.project.vo.req;

import lombok.Data;

@Data
public class LoginReqVo {

    private String code;

    private String encryptedData;

    private String iv;

    /**
     * 没有申请好小程序以前传此参数
     */
    private String openId;

}
