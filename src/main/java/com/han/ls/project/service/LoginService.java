package com.han.ls.project.service;

import com.han.ls.framework.security.LoginUser;
import com.han.ls.framework.web.service.TokenService;
import com.han.ls.project.domain.User;
import com.han.ls.project.mapper.UserMapper;
import com.han.ls.project.vo.req.LoginReqVo;
import com.han.ls.project.vo.resp.LoginRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenService tokenService;

    public LoginRespVo login(LoginReqVo reqVo) {
        // TODO 获取微信用户信息 拿到openid等

        String openId = "1";
        User user = userMapper.selectByOpenId(openId);
        if (user == null) {
            // TODO 注册操作 注册完成重新给user赋值
        }
        //使用Authentication的实现类
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getOpenId(), user.getOpenId());
        //手动调用方法去认证 他会自动调用UserDetailsService查 然后对比啥的
        Authentication authenticate = authenticationManager.authenticate(authentication);

        if (authenticate == null) {
            // TODO 抛出异常信息
        }
        // 拿到用户信息 然后生成jwt返回给前端
        // 这个其实就是UserDetails 也就是LoginUser
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        return tokenService.createLoginUser(loginUser);
    }
}
