package com.han.ls.framework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // @Autowired
    // private UnauthorizedEntryPoint unauthorizedEntryPoint;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private LsAuthenticationEntryPoint lsAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers(
                        "/login",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/**",
                        "/lsImgUpload/**",
                        "/favicon.ico",
                        "/case/list",
                        "/task/list"
                ).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().disable();
        //异常处理
        http.exceptionHandling()
                // 当用户匿名访问资源时（即不登陆去访问资源），返回异常信息
                .authenticationEntryPoint(lsAuthenticationEntryPoint)
                // 已经认证的用户访问自己没有权限的资源处理
                // .accessDeniedHandler((httpServletRequest, httpServletResponse, e) ->
                //         ResponseUtils.responseReturn(httpServletResponse, Result.error(ResultEnum.FORBIDDEN.getCode(), ResultEnum.FORBIDDEN.getMsg())))
                .and().addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MyPasswordEncoder();
    }
}
