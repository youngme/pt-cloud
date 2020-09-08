package com.bin.cloud.business.material.base.entity.vo;

import com.bin.cloud.business.material.base.entity.po.BuildingProve;
import com.bin.cloud.business.material.base.entity.po.BuildingUnitType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-04-30 13:39
 * @Version 1.0
 **/
@Data
public class BuildingInfoVo {

    private Long id = 0L;
    private String buildingName;            // 楼盘名称
    private String buildingAddress;         // 楼盘地址
    private String lowestTotalPrice;        // 最低总价
    private String lowestPrice;             // 最低单价
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date handOverTime;              // 交房时间
    private String propertyYear;            // 产权年限
    private String volumeRate;              // 容积率
    private String greeningRate;            // 绿化率
    private String totalConstructionArea;   // 总建筑面积
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date openQuotationTime;         // 开盘时间
    private List<String> buildingImgList;      // 楼盘图片集合
    private String buildingType;            // 楼盘类型
    private String payStatus;               // 楼盘销售状态
    private String decorate;                // 楼盘装修
    private String feature;                 // 楼盘特色
    private String periphery;               // 周边配套
    private String houseType;               // 户型
    private String area;               // 面积范围
    private String phone;                   // 联系电话
    private String propertyName;                // 物业公司
    private String propertyCost;                // 物业费用
    private Integer parkingSpace;               // 车位数量
    private List<String> stImgUrls;                // 沙盘图片
    private List<BuildingProveVo> proves;         // 楼盘证书集合

    private Long collectId;             // 收藏ID
    private Integer readCount;          // 浏览量

    private List<BuildingUnitTypeVo> unitTypes;   // 楼盘户型集合
}
