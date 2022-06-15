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
    public R<?> add(@RequestBody @Validated AddCaseReqVo reqVo) {
        return R.success(caseService.add(reqVo));
    }

    @PostMapping("/list")
    public R<?> list(@RequestBody @Validated CaseListReqVo reqVo) {
        return R.success(new PageInfo<>(caseService.list(reqVo)));
    }
    @PostMapping("/collectList")
    public R<?> collectList(@RequestBody @Validated CaseListReqVo reqVo) {
        return R.success(new PageInfo<>(caseService.collectList(reqVo)));
    }

    @GetMapping("/collectOrUndo")
    public R<?> collectOrUndo(int id) {
        caseService.collectOrUndo(id);
        return R.success();
    }

}
