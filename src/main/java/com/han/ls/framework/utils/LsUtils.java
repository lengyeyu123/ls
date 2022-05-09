package com.han.ls.framework.utils;

import com.han.ls.framework.security.LoginUser;
import com.han.ls.project.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class LsUtils {

    public static User getLoginUser() {
        return ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

}
