package com.han.ls.common.constant;

import lombok.Getter;

@Getter
public enum ResultStatus {

    OK(20000, "成功"),

    ERROR(50000, "服务器内部错误"),
    ;

    //用户模块错误码 50100~50199
    //商品模块错误码 50200~50299
    //订单模块错误码 50300~50399

    private final Integer code;
    private final String msg;

    ResultStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
