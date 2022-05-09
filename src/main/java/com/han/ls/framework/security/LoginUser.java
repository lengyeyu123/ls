package com.han.ls.framework.security;

import com.han.ls.project.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(); //该用户有哪些权限
    }

    @Override
    public String getPassword() {
        return this.user.getOpenId();
    }

    @Override
    public String getUsername() {
        return this.user.getOpenId();
    }

    @Override
    public boolean isAccountNonExpired() {//帐号是不是没有过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //是不是没有被锁定
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //凭证是不是没有过期
        return true;
    }

    @Override
    public boolean isEnabled() {  //是否可用
        return true;
    }
}
