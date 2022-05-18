package com.han.ls.project.controller;

import com.han.ls.framework.web.domain.R;
import com.han.ls.project.service.LoginService;
import com.han.ls.project.vo.req.LoginReqVo;
import com.han.ls.project.vo.resp.LoginRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public R<LoginRespVo> login(@RequestBody LoginReqVo reqVo) {
        return R.success(loginService.login(reqVo));
    }

}
