package com.han.ls.framework.utils;

import com.han.ls.framework.security.LoginUser;
import com.han.ls.project.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class LsUtils {

    public static User getLoginUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            log.info("未获取到当前登陆用户SpringSecurity中的用户信息为{}", principal);
            return null;
        }
        if (principal instanceof LoginUser) {
            return ((LoginUser) principal).getUser();
        }
        return null;
    }

}
