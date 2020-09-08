package com.bin.cloud.business.material.base.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Description 建材信息
 * @Author hubin
 * @Date 2019-10-10 14:08
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JccMaterialInfoDTO{

    private Long relationId;        // 关系ID
    private Long userId;            // 用户ID
    private Long materialId;        // 信息ID
    private String title;           // 标题
    private String price;       // 价格
    private String content;         // 内容
    private String city;            // 市
    private String district;        // 区
    private String county;          // 县
    private String town;            // 镇
    private String address;         // 详细地址
    private String phone;           // 手机号
    private String imgId;          // 图片ID
    private String description;     // 描述
    private Integer type;           // 信息类型
    private String businessName;    // 商家名称
}
