package com.han.ls;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
//@EnableWebMvc
@MapperScan("com.han.ls.project.**.mapper")
public class LsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LsApplication.class, args);
    }

    // tomcat配置 nio2
    // @Bean
    // public WebServerFactoryCustomizer webServerFactoryCustomizer() {
    //     return factory -> {
    //         ((TomcatServletWebServerFactory) factory).setProtocol("org.apache.coyote.http11.Http11Nio2Protocol");
    //     };
    // }

}
