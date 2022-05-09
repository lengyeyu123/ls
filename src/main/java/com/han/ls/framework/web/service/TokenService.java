package com.han.ls.framework.web.service;

import com.han.ls.common.constant.LsConstants;
import com.han.ls.common.utils.JwtUtils;
import com.han.ls.framework.config.properties.TokenProperties;
import com.han.ls.framework.security.LoginUser;
import com.han.ls.project.mapper.UserMapper;
import com.han.ls.project.vo.resp.LoginRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TokenService {

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过用户的基本信息生成jwt
     *
     * @param loginUser 登录用户信息
     * @return token
     */
    public LoginRespVo createLoginUser(LoginUser loginUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("openId", loginUser.getUser().getOpenId());
        claims.put("userName", loginUser.getUser().getUserName());
        String accessToken = JwtUtils.generateJwt(tokenProperties.getAtConfig(), claims);
        String refreshToken = JwtUtils.generateJwt(tokenProperties.getRtConfig(), claims);
        return new LoginRespVo()
                .setAccessToken(accessToken)
                .setRefreshToken(refreshToken)
                .setUser(loginUser.getUser());
    }

    /**
     * 验证jwt
     *
     * @param jwt       jwt字符串
     * @param tokenType token类型
     * @return 通过验证返回true
     */
    public boolean verifyToken(String jwt, String tokenType) {
        TokenProperties.TokenConfig tokenConfig = tokenType.equals(LsConstants.ACCESS_TOKEN_TYPE) ? tokenProperties.getAtConfig() : tokenProperties.getRtConfig();
        JwtUtils.parseJwt(jwt, tokenConfig);
        return true;
    }

    public String getOpenId(String token) {
        return (String) JwtUtils.parseJwt(token, tokenProperties.getAtConfig()).get("openId");
    }
}
