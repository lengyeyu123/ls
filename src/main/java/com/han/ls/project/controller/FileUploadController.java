package com.han.ls.project.controller;

import com.han.ls.common.constant.LsConstants;
import com.han.ls.common.exception.ServiceException;
import com.han.ls.common.utils.DateUtils;
import com.han.ls.framework.config.LsConfig;
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

@Slf4j
@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private LsConfig lsConfig;

    /**
     * fullFilePath = FILE_UPLOAD_PATH + relativePath + newFileName;
     *
     * @param files
     * @param fileType
     * @return
     */
    @SneakyThrows
    @PostMapping("/image/batch")
    public R handleImagesUpload(@RequestParam(required = false, name = "files") MultipartFile[] files,
                                @RequestParam(name = "fileType") String fileType) {
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
        String fileBaseName = DateUtils.dateTimeNow();
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
        return R.success(visitBaseUrlList);
    }


}
