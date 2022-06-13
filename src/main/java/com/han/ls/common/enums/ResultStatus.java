package com.han.ls.common.enums;

import lombok.Getter;

@Getter
public enum ResultStatus {

    OK(0, "成功"),

    ERROR(50000, "服务器内部错误"),

    INVALID_TOKEN(-1, "未登录，请登录后使用"),

    ACCOUNT_ERROR(50101, "账户异常"),

    VIOLATION_CONTENT(50102, "您上传的内容不安全，请重新编辑后上传"),

    ;


    //用户模块错误码 50100~50199
    //商品模块错误码 50200~50299
    //订单模块错误码 50300~50399

    private final Integer code;
    private final String message;

    ResultStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
