package com.han.ls.project.mapper;

import com.han.ls.project.domain.Case;
import com.han.ls.project.domain.UserCase;
import com.han.ls.project.vo.req.CaseListReqVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CaseMapper {

    void add(Case lsCase);

    List<Case> list(CaseListReqVo reqVo);

    void collectCase(UserCase userCase);

    void unCollectCase(@Param("userId") int userId, @Param("caseId") int caseId);

    List<UserCase> selectUserCase(@Param("userId") int userId, @Param("caseIdList") List<Integer> caseIdList);

    int countByUserIdAndCaseId(@Param("userId") int userId, @Param("caseId") int caseId);
}
