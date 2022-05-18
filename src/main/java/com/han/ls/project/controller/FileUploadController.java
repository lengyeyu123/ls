package com.han.ls.project.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.han.ls.common.constant.LsConstants;
import com.han.ls.common.enums.ResultStatus;
import com.han.ls.common.exception.ServiceException;
import com.han.ls.common.utils.DateUtils;
import com.han.ls.framework.config.LsConfig;
import com.han.ls.framework.config.WxMaConfiguration;
import com.han.ls.framework.config.properties.WxMaProperties;
import com.han.ls.framework.utils.LsUtils;
import com.han.ls.framework.web.domain.R;
import com.han.ls.project.domain.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private LsConfig lsConfig;

    @Autowired
    private WxMaProperties wxMaProperties;

    /**
     * fullFilePath = FILE_UPLOAD_PATH + relativePath + newFileName;
     *
     * @param files
     * @param fileType
     * @return
     */
    @SneakyThrows
    @PostMapping("/image/batch")
    public R<List<String>> handleImagesUpload(@RequestParam(required = false, name = "files") MultipartFile[] files, @RequestParam(name = "fileType") String fileType) {
        if (files.length == 0) {
            throw new ServiceException("上传文件为空，请选择文件");
        }
        // 验证文件格式
        for (MultipartFile file : files) {
            String contentType = file.getContentType();
            log.info("上传文件类型为 {}", contentType);
            String s = StringUtils.substringBefore(contentType, "/");
            if (!s.equals("image")) {
                throw new ServiceException("文件类型错误，请上传图片");
            }
        }

        String uploadFilePathPrefix = "";
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.startsWith("win")) {
            uploadFilePathPrefix = lsConfig.getUploadFilePathWinPrefix();
        } else {
            uploadFilePathPrefix = lsConfig.getUploadFilePathLinuxPrefix();
        }

        User loginUser = LsUtils.getLoginUser();
        List<String> visitBaseUrlList = new ArrayList<>();
        // String fileBaseName = DateUtils.dateTimeNow();
        String fileBaseName = UUID.randomUUID().toString().replaceAll("-", "");
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String fileName = file.getOriginalFilename();
            // 后缀名 .png
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 完整文件名 aaa.png
            String newFileName = fileBaseName + "-" + i + suffixName;
            // 相对路径
            String relativePath = fileType + "/" + DateUtils.dateTimeNow(DateUtils.YYYYMMDD) + "/" + loginUser.getOpenId() + "/";
            // 文件夹是否存在
            File fileDir = new File(uploadFilePathPrefix + relativePath);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            String realFullFilePath = uploadFilePathPrefix + relativePath + newFileName;
            file.transferTo(new File(realFullFilePath));

            visitBaseUrlList.add(LsConstants.FILE_VISIT_PREFIX + relativePath + newFileName);
        }

        boolean checkImgPass = true;
        // 验证图片是否安全
        for (String visitBaseUrl : visitBaseUrlList) {
            if (!checkImage(visitBaseUrl)) {
                // 未通过检测
                checkImgPass = false;
                break;
            }
        }
        if (checkImgPass) {
            return R.success(visitBaseUrlList);
        } else {
            throw new ServiceException(ResultStatus.VIOLATION_CONTENT);
        }
    }

    /**
     * 检测图片内容是否安全
     */
    @SneakyThrows
    private boolean checkImage(String visitBaseUrl) {
        // 验证用户上传信息是否安全
        List<WxMaProperties.Config> configs = this.wxMaProperties.getConfigs();
        // appid
        String appid = configs.get(0).getAppid();
        // 只有一个小程序的配置
        WxMaService maService = WxMaConfiguration.getMaService(appid);
        return maService.getSecCheckService().checkImage(lsConfig.getServerBaseUrl() + visitBaseUrl);
    }


}
