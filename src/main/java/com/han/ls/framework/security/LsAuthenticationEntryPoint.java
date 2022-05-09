package com.han.ls.framework.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.han.ls.common.enums.ResultStatus;
import com.han.ls.framework.web.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LsAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("未登录访问api，请求地址{}", request.getRequestURI());
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(R.error(ResultStatus.INVALID_TOKEN)));
    }
}
