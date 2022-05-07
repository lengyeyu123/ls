package com.han.ls.framework.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("token")
public class TokenProperties {

    private TokenConfig atConfig;

    private TokenConfig rtConfig;

    @Data
    public static class TokenConfig {

        private String header;

        private String secret;

        private long expireTime;

    }

}
