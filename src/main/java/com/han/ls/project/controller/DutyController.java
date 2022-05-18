package com.han.ls.project.controller;

import com.han.ls.framework.web.domain.R;
import com.han.ls.project.domain.Duty;
import com.han.ls.project.service.DutyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/duty")
@Slf4j
public class DutyController {

    @Autowired
    private DutyService dutyService;

    @GetMapping("all")
    public R<List<Duty>> all() {
        return R.success(dutyService.all());
    }

    @PostMapping("/add")
    public R<?> add(@RequestBody @Validated Duty duty) {
        dutyService.add(duty);
        return R.success();
    }

    @PostMapping("/update")
    public R<?> update(@RequestBody Duty duty) {
        dutyService.update(duty);
        return R.success();
    }

}
