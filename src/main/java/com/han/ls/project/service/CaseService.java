package com.han.ls.project.service;

import com.github.pagehelper.PageHelper;
import com.han.ls.common.utils.JsonUtils;
import com.han.ls.framework.utils.LsUtils;
import com.han.ls.project.domain.Case;
import com.han.ls.project.domain.User;
import com.han.ls.project.mapper.CaseMapper;
import com.han.ls.project.vo.req.AddCaseReqVo;
import com.han.ls.project.vo.req.CaseListReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return list;
    }
}
