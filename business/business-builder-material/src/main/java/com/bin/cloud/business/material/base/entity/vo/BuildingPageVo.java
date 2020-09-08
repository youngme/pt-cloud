package com.bin.cloud.business.material.base.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-05-09 14:23
 * @Version 1.0
 **/
@Data
public class BuildingPageVo {
    private Long id = 0L;
    private String buildingName;            // 楼盘名称
    private String buildingAddress;         // 楼盘地址
    private String lowestTotalPrice;        // 最低总价
    private String lowestPrice;             // 最低单价
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date openQuotationTime;         // 开盘时间
    private List<String> buildingImgList;      // 楼盘图片集合
    private String buildingType;            // 楼盘类型
    private String payStatus;               // 楼盘销售状态
    private String decorate;                // 楼盘装修
    private String feature;                 // 楼盘特色
    private String periphery;               // 周边配套
    private String houseType;               // 户型
    private String phone;                   // 联系电话
    private String area;                    // 面积
    private int type = -1;
    private Integer readCount;              // 浏览量
}
