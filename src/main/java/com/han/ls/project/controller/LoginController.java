package com.han.ls.project.controller;

import com.han.ls.framework.web.domain.R;
import com.han.ls.project.service.LoginService;
import com.han.ls.project.vo.req.LoginReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public R login(@RequestBody LoginReqVo reqVo) {
        return R.success(loginService.login(reqVo));
    }

}
