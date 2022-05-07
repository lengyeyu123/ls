package com.han.ls.framework.web.domain;

import com.han.ls.common.enums.ResultStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class R<T> implements Serializable {

    /**
     * 状态码
     */
    private int code;

    /**
     * 前端通知消息
     */
    private String msg;

    /**
     * 默认信息，exception中携带的消息
     */
    private String defaultMsg;

    private T data;

    private R() {
    }

    public static <T> R<T> success() {
        R<T> r = new R<>();
        r.setCode(ResultStatus.OK.getCode());
        r.setMsg(ResultStatus.OK.getMsg());
        return r;
    }

    public static <T> R<T> success(T data) {
        R<T> r = new R<>();
        r.setCode(ResultStatus.OK.getCode());
        r.setMsg(ResultStatus.OK.getMsg());
        return r;
    }

    /**
     * 成功，默认状态码，自定义返回消息，无返回数据
     *
     * @param msg 自定义返回消息
     * @param <T> 返回类泛型
     * @return 通用返回R
     */
    public static <T> R<T> success(String msg) {
        R<T> r = new R<>();
        r.setCode(ResultStatus.OK.getCode());
        r.setMsg(msg);
        return r;
    }

    public static <T> R<T> success(String msg, T data) {
        R<T> r = new R<>();
        r.setCode(ResultStatus.OK.getCode());
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static <T> R<T> error(Exception e) {
        R<T> r = new R<>();
        r.setCode(ResultStatus.ERROR.getCode());
        r.setMsg(ResultStatus.ERROR.getMsg());
        r.setDefaultMsg(e.getMessage());
        return r;
    }

    public static <T> R<T> error(ResultStatus resultStatus) {
        R<T> r = new R<>();
        r.setCode(resultStatus.getCode());
        r.setMsg(resultStatus.getMsg());
        return r;
    }

}
