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

    /**
     * 例如 服务/专业技术服务/测绘服务
     */
    private String type;

    private String bidUrl;

    private String bidContent;

    private String address;

    private Date createTime;

}
