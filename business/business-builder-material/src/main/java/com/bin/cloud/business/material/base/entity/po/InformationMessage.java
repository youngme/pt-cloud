package com.bin.cloud.business.material.base.entity.po;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @Description 资讯信息
 * @Author hubin
 * @Date 2020-04-12 09:57
 * @Version 1.0
 **/

@Data
public class InformationMessage {
    private Long id = 0L;
    private String type;        // 资讯类型
    private String title;       // 标题
    private String imgUrl;     // 图片地址
    private Long readCount;   // 阅读量
    private String createdBy;   // 创建人
    private Date createdTime = Date.from(ZonedDateTime.now().toInstant());
}
