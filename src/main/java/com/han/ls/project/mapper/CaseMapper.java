package com.han.ls.project.mapper;

import com.han.ls.project.domain.Case;
import com.han.ls.project.vo.req.CaseListReqVo;

import java.util.List;

public interface CaseMapper {

    void add(Case lsCase);

    List<Case> list(CaseListReqVo reqVo);
}
