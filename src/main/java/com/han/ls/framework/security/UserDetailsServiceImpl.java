package com.han.ls.framework.security;

import com.han.ls.project.domain.User;
import com.han.ls.project.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String openId) throws UsernameNotFoundException {
        if (StringUtils.isBlank(openId)) {
            // 请求未携带openId 系统异常
            log.error("未携带openId请求异常");
            throw new UsernameNotFoundException("openId为空");
        }
        User user = userMapper.selectByOpenId(openId);
        if (Objects.isNull(user)) {
            // 系统异常 未成功注册用户 或 openId错误
            log.error("根据openid {} 未查询到用户", openId);
            throw new UsernameNotFoundException("根据openId：" + openId + "未查找到用户");
        }
        return new LoginUser(user);
    }
}
