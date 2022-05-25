package com.han.ls.project.vo.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import java.util.List;

@Data
@Accessors(chain = true)
public class StandardUpdateReqVo {

    @Min(1)
    private int id;

    @Min(1)
    private int dutyId;

    private String description;

    private List<String> descImgArr;

    private String descImgs;


}
