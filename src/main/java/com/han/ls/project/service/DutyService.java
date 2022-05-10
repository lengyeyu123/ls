package com.han.ls.project.service;

import com.han.ls.project.domain.Duty;
import com.han.ls.project.mapper.DutyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DutyService {

    @Autowired
    private DutyMapper dutyMapper;

    public List<Duty> allDuty() {
        return dutyMapper.allDuty();
    }
}
