package com.han.ls.project.controller;

import com.han.ls.project.service.CaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/case")
@Slf4j
public class CaseController {

    @Autowired
    private CaseService caseServicea;

}
