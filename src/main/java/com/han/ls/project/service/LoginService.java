package com.han.ls.project.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.han.ls.common.exception.ServiceException;
import com.han.ls.framework.config.WxMaConfiguration;
import com.han.ls.framework.config.properties.WxMaProperties;
import com.han.ls.framework.security.LoginUser;
import com.han.ls.framework.web.service.TokenService;
import com.han.ls.project.domain.User;
import com.han.ls.project.mapper.UserMapper;
import com.han.ls.project.vo.req.LoginReqVo;
import com.han.ls.project.vo.resp.LoginRespVo;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private WxMaProperties wxMaProperties;

    /**
     * 登录
     * @param reqVo
     * @return
     */
    public LoginRespVo login(LoginReqVo reqVo) {
        WxMaUserInfo wxMaUserInfo = null;
        // 测试账号openId有值
        if (StringUtils.isBlank(reqVo.getOpenId())) {
            wxMaUserInfo = getWxMaUserInfo(reqVo.getCode(), reqVo.getEncryptedData(), reqVo.getIv());
        }
        String openId = wxMaUserInfo == null ? reqVo.getOpenId() : wxMaUserInfo.getOpenId();
        User user = userMapper.selectByOpenId(openId);
        if (user == null) {
            user = userService.register(wxMaUserInfo);
        }
        //使用Authentication的实现类
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getOpenId(), user.getOpenId());
        //手动调用方法去认证 他会自动调用UserDetailsService查 然后对比啥的
        Authentication authenticate = authenticationManager.authenticate(authentication);

        if (authenticate == null) {
            // 这里不应该出现异常，无论新注册用户或者老用户，都应该能查到
            throw new ServiceException();
        }
        // 拿到用户信息 然后生成jwt返回给前端
        // 这个其实就是UserDetails 也就是LoginUser
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        return tokenService.createLoginUser(loginUser);
    }


    private WxMaUserInfo getWxMaUserInfo(String code, String encryptedData, String iv) {
        List<WxMaProperties.Config> configs = this.wxMaProperties.getConfigs();
        // appid
        String appid = configs.get(0).getAppid();
        // 只有一个小程序的配置
        WxMaService maService = WxMaConfiguration.getMaService(appid);
        // 获取到unionid openid sessionKey
        WxMaJscode2SessionResult jscode2session = null;
        try {
            jscode2session = WxMaConfiguration.getMaService(appid).jsCode2SessionInfo(code);
        } catch (WxErrorException e) {
            log.error("获取微信服务器用户信息失败", e);
        }
        // 获取微信用户的微信相关信息
        WxMaUserInfo userInfo = maService.getUserService().getUserInfo(jscode2session.getSessionKey(), encryptedData, iv);
        userInfo.setOpenId(jscode2session.getOpenid());
        userInfo.setUnionId(jscode2session.getUnionid());
        return userInfo;
    }
}
