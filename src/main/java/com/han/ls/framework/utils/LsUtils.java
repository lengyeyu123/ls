package com.han.ls.framework.utils;

import com.han.ls.framework.security.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class LsUtils {

    public static LoginUser getLoginUser() {
        return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
