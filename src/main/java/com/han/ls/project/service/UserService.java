package com.han.ls.project.service;

import com.han.ls.common.utils.DateUtils;
import com.han.ls.project.domain.User;
import com.han.ls.project.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> list() {
        return userMapper.list();
    }

    public void add(String userName) {
        userMapper.add(new User().setUserName(userName)
                .setCreateTime(DateUtils.getNowDate()));
    }
}
