package com.han.ls.project.service;

import com.han.ls.common.utils.JsonUtils;
import com.han.ls.project.domain.Duty;
import com.han.ls.project.domain.DutyStandard;
import com.han.ls.project.mapper.DutyMapper;
import com.han.ls.project.vo.req.StandardAddReqVo;
import com.han.ls.project.vo.req.StandardUpdateReqVo;
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

    @SneakyThrows
    public List<Duty> all() {
        Thread.sleep(9000L);

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

        if (!StringUtils.isBlank(duty.getName()) || !StringUtils.isBlank(duty.getStandardDesc())) {
            dutyMapper.update(duty);
        }
    }

    public Duty selectById(int dutyId) {
        return dutyMapper.selectById(dutyId);
    }

    public List<DutyStandard> standardAll() {
        List<DutyStandard> list = dutyMapper.standardAll();
        list.forEach(o -> {
            o.setDescImgArr(JsonUtils.josn2StrList(o.getDescImgs()));
        });
        return list;
    }

    public void standardAdd(StandardAddReqVo reqVo) {
        DutyStandard dutyStandard = new DutyStandard();
        if (!reqVo.getDescImgArr().isEmpty()) {
            String json = JsonUtils.toJson(reqVo.getDescImgArr());
            dutyStandard.setDescImgs(json);
        }
        dutyStandard.setDescription(reqVo.getDescription());
        dutyStandard.setDuty(new Duty().setId(reqVo.getDuty_id()));
        dutyMapper.standardAdd(dutyStandard);
    }

    public void standardUpdate(StandardUpdateReqVo reqVo) {
        if (reqVo.getDutyId() != 0 && StringUtils.isNotBlank(reqVo.getDescription()) && !reqVo.getDescImgArr().isEmpty()) {
            if (!reqVo.getDescImgArr().isEmpty()) {
                reqVo.setDescImgs(JsonUtils.toJson(reqVo.getDescImgArr()));
            }
            dutyMapper.standardUpdate(reqVo);
        }
    }

    public Duty selectByName(String name) {
        return dutyMapper.selectByName(name);
    }

    public DutyStandard getStandardByDutyName(String name) {
        Duty duty = dutyMapper.selectByName(name);
        return dutyMapper.getStandardById(duty.getId());
    }
}
