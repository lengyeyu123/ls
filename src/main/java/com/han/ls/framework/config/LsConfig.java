package com.han.ls.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ls")
public class LsConfig {

    /**
     * 项目名称
     */
    private String name;

    /**
     * 版本
     */
    private String version;

    /**
     * 版权年份
     */
    private String copyrightYear;

    /**
     * 上传图片保存文件夹路径
     */
    private String uploadFilePathWinPrefix;
    private String uploadFilePathLinuxPrefix;

    /**
     * 本服务访问基础路径
     */
    private String serverBaseUrl;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public String getUploadFilePathWinPrefix() {
        return uploadFilePathWinPrefix;
    }

    public void setUploadFilePathWinPrefix(String uploadFilePathWinPrefix) {
        this.uploadFilePathWinPrefix = uploadFilePathWinPrefix;
    }

    public String getUploadFilePathLinuxPrefix() {
        return uploadFilePathLinuxPrefix;
    }

    public void setUploadFilePathLinuxPrefix(String uploadFilePathLinuxPrefix) {
        this.uploadFilePathLinuxPrefix = uploadFilePathLinuxPrefix;
    }

    public String getServerBaseUrl() {
        return serverBaseUrl;
    }

    public void setServerBaseUrl(String serverBaseUrl) {
        this.serverBaseUrl = serverBaseUrl;
    }
}
