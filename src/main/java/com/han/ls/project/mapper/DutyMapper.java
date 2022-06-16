package com.han.ls.project.mapper;

import com.han.ls.project.domain.Duty;
import com.han.ls.project.domain.DutyStandard;
import com.han.ls.project.vo.req.StandardUpdateReqVo;

import java.util.List;

public interface DutyMapper {

    List<Duty> all();

    void add(Duty duty);

    void update(Duty duty);

    Duty selectById(int dutyId);

    List<DutyStandard> standardAll();

    void standardAdd(DutyStandard dutyStandard);

    void standardUpdate(StandardUpdateReqVo reqVo);

    Duty selectByName(String name);

    DutyStandard getStandardById(int id);
}
