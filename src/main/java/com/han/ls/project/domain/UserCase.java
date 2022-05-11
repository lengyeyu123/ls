package com.han.ls.project.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class UserCase {

    private int id;

    private int userId;

    private int caseId;

    private Date createTime;

}
