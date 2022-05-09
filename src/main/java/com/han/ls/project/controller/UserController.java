package com.han.ls.project.controller;

import com.han.ls.framework.security.LoginUser;
import com.han.ls.framework.utils.LsUtils;
import com.han.ls.framework.web.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @GetMapping("/getUserInfo")
    public R<LoginUser> getUserInfo() {
        LoginUser loginUser = LsUtils.getLoginUser();
        return R.success(loginUser);
    }

}
