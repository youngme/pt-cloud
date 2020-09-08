package com.bin.cloud.business.material.base.entity.query;

import com.bin.cloud.business.material.base.entity.po.InformationMessageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-04-12 10:50
 * @Version 1.0
 **/
@Data
public class InformationMessageQuery {
    private String type;                // 资讯类型
    private String title;               // 标题
    private String imgUrl;             // 图片地址
    private List<InformationMessageInfo> contents;
    private String readCount;           // 阅读量
    private String createdBy;           // 创建人
    private Date createdTime;           // 创建时间
}
