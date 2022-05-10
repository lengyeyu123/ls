package com.han.ls.framework.config;

import com.han.ls.common.constant.LsConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LsWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private LsConfig lsConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String uploadFilePathPrefix = "";
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.startsWith("win")) {
            uploadFilePathPrefix = lsConfig.getUploadFilePathWinPrefix();
        } else {
            uploadFilePathPrefix = lsConfig.getUploadFilePathLinuxPrefix();
        }
        registry.addResourceHandler(LsConstants.FILE_VISIT_PREFIX + "**").addResourceLocations("file:" + uploadFilePathPrefix);
    }


}
