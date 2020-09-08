package com.bin.cloud.business.material.base.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description 建材信息列表VO
 * @Author hubin
 * @Date 2019-11-05 18:02
 * @Version 1.0
 **/
@Data
public class JccMaterialVo {
    private Long id;                // ID
    private String title;           // 标题
    private Integer type;           // 信息类型
    private String price;       // 价格
    private String content;         // 内容
    private String city;            // 市
    private String district;        // 区
    private String county;          // 县
    private String town;            // 镇
    private String address;         // 详细地址
    private String phone;           // 手机号
    private List<String> imgUrls;   // 图片List
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;       // 更新时间
    private String businessName;    // 商家名称
    private String contact;         // 联系人
    private Long collectId;             // 收藏ID
    private Integer readCount;      // 浏览量
}
