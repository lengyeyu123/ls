package com.han.ls.project.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class County {

    private int id;

    private String code;

    private String name;

    private int provinceId;

    private String provinceName;

    private String provinceCode;

    private int cityId;

    private String cityName;

    private String cityCode;

}
