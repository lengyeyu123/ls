package com.han.ls.project.vo.resp;

import com.han.ls.project.domain.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginRespVo {

    private String accessToken;

    private String refreshToken;

    private User user;

}
