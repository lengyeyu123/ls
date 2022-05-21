package com.han.ls.project.mapper;

import com.han.ls.project.domain.Duty;

import java.util.List;

public interface DutyMapper {

    List<Duty> all();

    void add(Duty duty);

    void update(Duty duty);

    Duty selectById(int dutyId);
}
