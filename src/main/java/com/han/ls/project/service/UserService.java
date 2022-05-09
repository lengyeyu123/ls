package com.han.ls.project.service;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.han.ls.common.utils.DateUtils;
import com.han.ls.project.domain.User;
import com.han.ls.project.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public void insertUsers(List<User> list) {
        for (User user : list) {
            userMapper.add(user);
        }
    }

    public User register(WxMaUserInfo wxMaUserInfo) {
        String openId = wxMaUserInfo.getOpenId();
        String unionId = wxMaUserInfo.getUnionId();
        String avatarUrl = wxMaUserInfo.getAvatarUrl();
        String nickName = wxMaUserInfo.getNickName();
        User user = new User();
        user.setUserName(nickName).setOpenId(openId).setUnionId(unionId).setAvatarUrl(avatarUrl).setCreateTime(new Date());
        userMapper.register(user);
        return user;
    }
}
