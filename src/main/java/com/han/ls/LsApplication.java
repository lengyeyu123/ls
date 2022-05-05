package com.han.ls;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@EnableWebMvc
@MapperScan("com.han.ls.project.**.mapper")
public class LsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LsApplication.class, args);
    }

}
