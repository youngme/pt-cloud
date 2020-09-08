package com.bin.cloud.business.material.base.entity.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 建材信息VO
 * @Author hubin
 * @Date 2019-10-11 16:49
 * @Version 1.0
 **/
@Data
@Alias("jccMaterialInfoVo")
public class JccMaterialInfoVo {
    private Long id;
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
    private String imgId;           // 图片ID
    private String description;     // 描述
    private Integer type;           // 信息类型
    private String createdBy;       // 更新人
    private Date createdTime;       // 更新时间
    private String businessName;    // 商家名称
    private String contact;         // 联系人
}
