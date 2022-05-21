package com.han.ls.project.controller;

import com.han.ls.framework.utils.LsUtils;
import com.han.ls.framework.web.domain.R;
import com.han.ls.framework.web.service.TokenService;
import com.han.ls.project.domain.User;
import com.han.ls.project.service.UserService;
import com.han.ls.project.vo.req.UpdateUserInfoReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 更新用户的基本信息
     * @param reqVo
     * @return
     */
    @PostMapping("/updateUserInfo")
    public R<?> updateUserInfo(@RequestBody UpdateUserInfoReqVo reqVo) {
        return R.success(userService.updateUserInfo(reqVo));
    }

    @GetMapping("/getUser")
    public R<User> getUser(int userId) {
        return R.success(userService.getUser(userId));
    }

    @GetMapping("/getLoginUser")
    public R<User> getLoginUser() {
        User loginUser = LsUtils.getLoginUser();
        // 根据用户id重新获取用户的信息，获取最新的用户信息 security中的信息未更新
        return R.success(userService.getUser(loginUser.getId()));
    }


    /**
     * 禁用用户
     */
    @GetMapping("/disabled")
    public R<?> disabled(int userId) {
        userService.disabled(userId);
        return R.success();
    }



}
