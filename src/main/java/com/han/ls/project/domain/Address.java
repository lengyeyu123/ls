package com.han.ls.project.domain;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
public class Address {

    private int id;

    private String code;

    private String name;

}
