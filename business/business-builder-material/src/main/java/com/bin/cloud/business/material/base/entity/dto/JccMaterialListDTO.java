package com.bin.cloud.business.material.base.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description
 * @Author hubin
 * @Date 2019-11-06 16:34
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("jccMaterialListDTO")
public class JccMaterialListDTO {
    private Long id;                // ID
    private String title;           // 标题
    private String price;       // 价格
    private String content;         // 内容
    private String city;            // 市
    private String district;        // 区
    private String county;          // 县
    private String town;            // 镇
    private String address;         // 详细地址
    private String phone;           // 手机号
    private String imgId;           // 图片ID
    private Date createdTime;       // 更新时间
}
