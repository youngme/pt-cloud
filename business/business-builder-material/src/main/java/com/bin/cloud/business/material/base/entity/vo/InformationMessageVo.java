package com.bin.cloud.business.material.base.entity.vo;

import com.bin.cloud.business.material.base.entity.po.InformationMessageInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-04-12 10:28
 * @Version 1.0
 **/
@Data
public class InformationMessageVo {
    private Long id;
    private String type;                // 资讯类型
    private String title;               // 标题
    private String imgUrl;       // 图片地址
    private List<InformationMessageInfo> contents;             // 内容
    private Long readCount;           // 阅读量
    private String createdBy;           // 创建人
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;           // 创建时间
}
