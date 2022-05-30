package com.han.ls.project.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.security.WxMaMsgSecCheckCheckRequest;
import cn.binarywang.wx.miniapp.bean.security.WxMaMsgSecCheckCheckResponse;
import com.github.pagehelper.PageHelper;
import com.han.ls.common.enums.ResultStatus;
import com.han.ls.common.exception.ServiceException;
import com.han.ls.common.utils.JsonUtils;
import com.han.ls.framework.config.WxMaConfiguration;
import com.han.ls.framework.config.properties.WxMaProperties;
import com.han.ls.framework.utils.LsUtils;
import com.han.ls.project.domain.Case;
import com.han.ls.project.domain.User;
import com.han.ls.project.domain.UserCase;
import com.han.ls.project.mapper.CaseMapper;
import com.han.ls.project.vo.req.AddCaseReqVo;
import com.han.ls.project.vo.req.CaseListReqVo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CaseService {

    @Autowired
    private WxMaProperties wxMaProperties;

    @Autowired
    private CaseMapper caseMapper;

    @SneakyThrows
    public void add(AddCaseReqVo reqVo) {
        User loginUser = LsUtils.getLoginUser();

        // 验证用户上传信息是否安全
        List<WxMaProperties.Config> configs = this.wxMaProperties.getConfigs();
        // appid
        String appid = configs.get(0).getAppid();
        // 只有一个小程序的配置
        WxMaService maService = WxMaConfiguration.getMaService(appid);

        WxMaMsgSecCheckCheckResponse checkResponse = maService.getSecCheckService().checkMessage(WxMaMsgSecCheckCheckRequest.builder()
                .version("2")
                .openid(loginUser.getOpenId())
                .scene(2)
                .content(reqVo.getDescription())
                .build());
        log.info("===checkResponse {}", checkResponse);

        WxMaMsgSecCheckCheckResponse.ResultBean checkResult = checkResponse.getResult();
        log.info("===checkResult {}", checkResult);

        if (!checkResult.getLabel().equals("100")) {
            throw new ServiceException(ResultStatus.VIOLATION_CONTENT);
        }

        caseMapper.add(new Case()
                .setUserId(loginUser.getId())
                .setDescription(reqVo.getDescription())
                .setCountyId(reqVo.getCountyId())
                .setAddress(reqVo.getAddress())
                .setDescImgs(JsonUtils.toJson(reqVo.getDescImgArr())));
    }

    public List<Case> list(CaseListReqVo reqVo) {
        PageHelper.startPage(reqVo);
        List<Case> list = caseMapper.list(reqVo);
        for (Case aCase : list) {
            aCase.setImgArr(JsonUtils.josn2StrList(aCase.getDescImgs()));
        }
        int userId = LsUtils.getLoginUser().getId();
        List<Integer> caseIdList = list.stream().map(Case::getId).collect(Collectors.toList());
        List<UserCase> userCaseList = caseMapper.selectUserCase(userId, caseIdList);
        for (UserCase userCase : userCaseList) {
            for (Case aCase : list) {
                if (userCase.getCaseId() == aCase.getId()) {
                    aCase.setCollectFlag(true);
                }
            }
        }
        return list;
    }

    @GetMapping("/collect")
    public void collectCase(int caseId) {
        caseMapper.collectCase(new UserCase().setCaseId(caseId).setUserId(LsUtils.getLoginUser().getId()).setCreateTime(new Date()));
    }

    @GetMapping("/unCollect")
    public void unCollectCase(int caseId) {
        caseMapper.unCollectCase(LsUtils.getLoginUser().getId(), caseId);
    }
}
