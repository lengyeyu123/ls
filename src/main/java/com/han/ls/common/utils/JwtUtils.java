package com.han.ls.common.utils;

import com.han.ls.framework.config.properties.TokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtils {

    /**
     * 生成JWT
     *
     * @param tokenConfig token配置信息
     * @param claims      负载信息Map
     * @return jwt字符串
     */
    public static String generateJwt(TokenProperties.TokenConfig tokenConfig, Map<String, Object> claims) {
        String secret = tokenConfig.getSecret();
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + tokenConfig.getExpireTime() * 60 * 1000L))
                .signWith(SignatureAlgorithm.HS512, secret).compact();

        return token;
//        JwtBuilder builder = Jwts.builder();
//        builder.setClaims(claims);
//        builder.setExpiration(new Date(System.currentTimeMillis() + tokenConfig.getExpireTime() * 60 * 1000L));
//        String secret = tokenConfig.getSecret();
//        String compact = builder.signWith(SignatureAlgorithm.HS256,secret).compact();
//        return compact;

//        return Jwts.builder()
//                .setClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis() + tokenConfig.getExpireTime() * 60 * 1000L))
//                .signWith(SignatureAlgorithm.HS512, tokenConfig.getSecret()).compact();
    }


    public static Claims parseJwt(String jwt, TokenProperties.TokenConfig tokenConfig) {
        return Jwts.parser()
                .setSigningKey(tokenConfig.getSecret())
                .parseClaimsJwt(jwt)
                .getBody();
    }

}
