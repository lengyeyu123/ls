package com.han.ls.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 上传文件枚举
 */
public enum UploadFileEnum {

    Case(),

    Task(),

    Avatar("user");

    private final String relativePath;

    private final String fileType;


    UploadFileEnum() {
        this.fileType = "image";
        this.relativePath = StringUtils.lowerCase(super.name()) + "/" + fileType + "/";
    }

    UploadFileEnum(String relativePath) {
        this.fileType = "image";
        this.relativePath = relativePath + "/" + StringUtils.lowerCase(super.name()) + "/" + fileType + "/";
    }

    public String getFileType() {
        return this.fileType;
    }

    public String getRelativePath() {
        return this.relativePath;
    }

}
