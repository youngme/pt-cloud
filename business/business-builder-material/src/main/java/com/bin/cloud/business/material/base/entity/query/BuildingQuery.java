package com.bin.cloud.business.material.base.entity.query;

import com.bin.cloud.business.material.base.entity.po.BuildingProve;
import com.bin.cloud.business.material.base.entity.po.BuildingUnitType;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-05-05 12:15
 * @Version 1.0
 **/
@Data
public class BuildingQuery {
    private String buildingName;            // 楼盘名称
    private String buildingAddress;         // 楼盘地址
    private String lowestTotalPrice;        // 最低总价
    private String lowestPrice;             // 最低单价
    private Date openQuotationTime;         // 开盘时间
    private Date handOverTime;              // 交房时间
    private String propertyYear;            // 产权年限
    private String volumeRate;              // 容积率
    private String greeningRate;            // 绿化率
    private String totalConstructionArea;   // 总建筑面积
    private String buildingImgs;            // 楼盘图片ID
    private String buildingType;            // 楼盘类型
    private String payStatus;               // 楼盘销售状态
    private String decorate;                // 楼盘装修
    private String feature;                 // 楼盘特色
    private String periphery;               // 周边配套
    private String houseType;               // 户型
    private String areaRange;               // 面积范围
    private String stImgIds;                // 沙盘图片

    private String phone;                   // 联系电话
    private String propertyName;                // 物业公司
    private String propertyCost;                // 物业费用
    private Integer parkingSpace;               // 车位数量

    private List<BuildingProve> proveList;          // 预售证书集合

    private List<BuildingUnitType> unitTypeList;    // 户型

    private Integer type;           // 类型
    private Long userId;            // 用户ID
}
