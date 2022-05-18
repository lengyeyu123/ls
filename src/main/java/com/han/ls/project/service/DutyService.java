package com.han.ls.project.service;

import com.han.ls.project.domain.Duty;
import com.han.ls.project.mapper.DutyMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Slf4j
@Service
public class DutyService {

    @Autowired
    private DutyMapper dutyMapper;

    public List<Duty> all() {
        return dutyMapper.all();
    }

    public void add(Duty duty) {
        dutyMapper.add(duty);
    }

    @SneakyThrows
    public void update(Duty duty) {
        if (duty.getId() <= 0) {
            throw new ValidationException("id不能为空");
        }

        if (!StringUtils.isBlank(duty.getName()) || !StringUtils.isBlank(duty.getDescription())) {
            dutyMapper.update(duty);
        }
    }
}
