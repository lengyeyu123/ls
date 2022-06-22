package com.han.ls.project.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Bid {

    private BigInteger id;

    private String title;

    private Date uploadTime;

    private String buyer;

    private String agentCompany;

}
