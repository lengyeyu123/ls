package com.han.ls.framework.web.exception;

import com.han.ls.common.exception.ServiceException;
import com.han.ls.framework.web.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                 HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return R.error(e);
    }

    /**
     * 参数验证异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',参数验证未通过", requestURI);
        return R.error(e);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public Object handleServiceException(ServiceException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return R.error(e);
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public R handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return R.error(e);
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return R.error(e);
    }


}