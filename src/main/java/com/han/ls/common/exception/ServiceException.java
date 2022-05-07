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

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServiceException() {
    }

    public ServiceException(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }

    public ResultStatus getResultStatus() {
        return this.resultStatus;
    }

}