package com.han.ls.common.exception;

import com.han.ls.common.enums.ResultStatus;

/**
 * 业务异常
 *
 * @author ruoyi
 */
public final class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ResultStatus resultStatus;

    private String msg;

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServiceException() {
        this.resultStatus = ResultStatus.ERROR;
    }

    /**
     * 自定义系统异常提示消息
     *
     * @param msg
     */
    public ServiceException(String msg) {
        this.msg = msg;
    }

    public ServiceException(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

    public ResultStatus getResultStatus() {
        return this.resultStatus;
    }

    public String getMsg() {
        return this.msg;
    }

}