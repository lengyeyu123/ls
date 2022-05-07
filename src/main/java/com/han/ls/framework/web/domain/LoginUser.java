package com.han.ls.framework.web.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginUser {

    private String accessToken;

    private String refreshToken;

}
