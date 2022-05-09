package com.han.ls.project.controller;

import com.han.ls.framework.web.domain.R;
import com.han.ls.project.service.UserService;
import com.han.ls.project.vo.req.BindPhoneReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 更新用户的基本信息
     * TODO 发送验证码验证手机号是否正确
     *
     * @param reqVo
     * @return
     */
    @PostMapping("/updateUserInfo")
    public R updateUserInfo(@RequestBody BindPhoneReqVo reqVo) {
        userService.updateUserInfo(reqVo);
        return R.success();
    }

}
