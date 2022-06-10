package com.han.ls.framework.utils;

import com.han.ls.framework.security.LoginUser;
import com.han.ls.project.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class LsUtils {

    public static User getLoginUser() {
        User user = null;
        try {
            user = ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        } catch (Exception e) {
            log.error("获取当前登陆用户失败", e);
        }
        return user;
    }

}
