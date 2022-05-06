package com.han.ls.project.service;

import com.han.ls.project.mapper.DutyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DutyService {

    @Autowired
    private DutyMapper dutyMapper;

}
