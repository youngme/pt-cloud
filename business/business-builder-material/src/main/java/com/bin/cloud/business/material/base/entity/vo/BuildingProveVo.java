package com.bin.cloud.business.material.base.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-05-11 16:49
 * @Version 1.0
 **/
@Data
public class BuildingProveVo {
    private String imgUrl;           // 证书图片ID集合
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date sendTime;           // 发证日期
}
