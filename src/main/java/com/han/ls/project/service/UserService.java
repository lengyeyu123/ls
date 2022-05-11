package com.han.ls.project.service;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.han.ls.common.constant.LsConstants;
import com.han.ls.common.exception.ServiceException;
import com.han.ls.common.utils.DateUtils;
import com.han.ls.framework.utils.LsUtils;
import com.han.ls.project.domain.User;
import com.han.ls.project.mapper.UserMapper;
import com.han.ls.project.vo.req.UpdateUserInfoReqVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

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


    public User updateUserInfo(UpdateUserInfoReqVo reqVo) {
        User loginUser = LsUtils.getLoginUser();
        if (StringUtils.isBlank(reqVo.getAddress()) && StringUtils.isBlank(reqVo.getPhone())
                && reqVo.getCountyId() == 0 && StringUtils.isBlank(reqVo.getWxCode())
                && reqVo.getDutyId() == 0
        ) {
            return loginUser;
        }

        if (StringUtils.isNotBlank(reqVo.getPhone())) {
            //用户手机号码校验
            if (Pattern.matches(LsConstants.REGEX_PHONE, reqVo.getPhone())) {
                //手机号码格式正确
                loginUser.setPhone(reqVo.getPhone());
            } else {
                //手机号码格式错误
                throw new ServiceException("手机号格式错误，请重新填写");
            }
        }

        loginUser.setAddress(reqVo.getAddress())
                .setCountyId(reqVo.getCountyId())
                .setWxCode(reqVo.getWxCode())
                .setDutyId(reqVo.getDutyId())
                .setUpdateTime(new Date());
        userMapper.updateUserInfo(loginUser);
        return loginUser;
    }

    public User getUser(int userId) {
        return userMapper.selectById(userId);
    }
}
