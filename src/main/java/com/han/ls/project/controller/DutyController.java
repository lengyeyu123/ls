package com.han.ls.project.controller;

import com.han.ls.project.service.DutyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/duty")
@Slf4j
public class DutyController {

    @Autowired
    private DutyService dutyService;

}
