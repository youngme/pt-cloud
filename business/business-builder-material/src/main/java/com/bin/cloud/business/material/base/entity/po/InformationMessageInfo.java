package com.bin.cloud.business.material.base.entity.po;

import lombok.Data;

/**
 * @Description 信息资讯详细信息
 * @Author hubin
 * @Date 2020-04-12 17:38
 * @Version 1.0
 **/
@Data
public class InformationMessageInfo {
    private Long id = 0L;
    private Long pjId;        // 资讯类型
    private String title;       // 标题
    private String imgUrl;     // 内容配图图片地址
    private String content;     // 内容
}
