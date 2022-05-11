package com.han.ls.project.service;

import com.github.pagehelper.PageHelper;
import com.han.ls.common.utils.JsonUtils;
import com.han.ls.framework.utils.LsUtils;
import com.han.ls.project.domain.Case;
import com.han.ls.project.domain.User;
import com.han.ls.project.domain.UserCase;
import com.han.ls.project.mapper.CaseMapper;
import com.han.ls.project.vo.req.AddCaseReqVo;
import com.han.ls.project.vo.req.CaseListReqVo;
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
    private CaseMapper caseMapper;

    public void add(AddCaseReqVo reqVo) {
        User loginUser = LsUtils.getLoginUser();
        Case aCase = new Case()
                .setUserId(loginUser.getId())
                .setDescription(reqVo.getDescription())
                .setCountyId(reqVo.getCountyId())
                .setAddress(reqVo.getAddress())
                .setDescImgs(JsonUtils.toJson(reqVo.getDescImgArr()));
        caseMapper.add(aCase);
    }

    public List<Case> list(CaseListReqVo reqVo) {
        PageHelper.startPage(reqVo.getPage(), reqVo.getPageSize());
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
        UserCase userCase = new UserCase().setCaseId(caseId).setUserId(LsUtils.getLoginUser().getId()).setCreateTime(new Date());
        caseMapper.collectCase(userCase);
    }

    @GetMapping("/unCollect")
    public void unCollectCase(int caseId) {
        int userId = LsUtils.getLoginUser().getId();
        caseMapper.unCollectCase(userId, caseId);
    }
}
