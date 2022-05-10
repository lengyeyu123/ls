package com.han.ls.project.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Duty {

    private int id;

    private String name;

    private String description;

}
