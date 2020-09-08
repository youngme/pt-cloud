package com.bin.cloud.business.material.base.entity.po;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @Description 楼盘物业
 * @Author hubin
 * @Date 2020-04-30 10:54
 * @Version 1.0
 **/
@Data
@Alias("buildingProperty")
public class BuildingProperty {

    private Long id = 0L;
    private String propertyName;        // 物业公司
    private String propertyCost;        // 物业费用
    private Integer parkingSpace;       // 车位数量

}
