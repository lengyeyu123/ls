package com.han.ls.project.controller;

import com.han.ls.framework.web.domain.R;
import com.han.ls.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO jackson序列化返回空字符串 空集合 空对象 [] {}
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;


    @GetMapping("/test")
    public Object test() {
        return userService.list();
    }

    @GetMapping("/addUser")
    public R<?> addUser(String userName) {
        userService.add(userName);
        return R.success();
    }

}
