package com.han.ls.project.mapper;

import com.han.ls.project.domain.User;

import java.util.List;

public interface UserMapper {

    List<User> list();

    void add(User user);

    User selectById(int id);

    User selectByOpenId(String openId);

    void register(User user);

    void updateUserInfo(User loginUser);
}
