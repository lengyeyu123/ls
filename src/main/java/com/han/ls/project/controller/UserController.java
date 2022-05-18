package com.han.ls.project.controller;

import com.han.ls.framework.web.domain.R;
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

    /**
     * 禁用用户
     */
    @GetMapping("/disabled")
    public R<?> disabled(int userId) {
        userService.disabled(userId);
        return R.success();
    }



}
