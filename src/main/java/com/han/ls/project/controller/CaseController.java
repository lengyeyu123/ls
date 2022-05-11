package com.han.ls.project.controller;

import com.github.pagehelper.PageInfo;
import com.han.ls.framework.web.domain.R;
import com.han.ls.project.service.CaseService;
import com.han.ls.project.vo.req.AddCaseReqVo;
import com.han.ls.project.vo.req.CaseListReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/case")
@Slf4j
public class CaseController {

    @Autowired
    private CaseService caseService;

    @PostMapping("/add")
    public R add(@RequestBody @Validated AddCaseReqVo reqVo) {
        caseService.add(reqVo);
        return R.success();
    }

    @PostMapping("/list")
    public R list(@RequestBody @Validated CaseListReqVo reqVo) {
        return R.success(new PageInfo<>(caseService.list(reqVo)));
    }

    @GetMapping("/collect")
    public R collect(int caseId) {
        caseService.collectCase(caseId);
        return R.success();
    }

    @GetMapping("/unCollect")
    public R unCollect(int caseId) {
        caseService.unCollectCase(caseId);
        return R.success();
    }

}
