package com.bin.cloud.business.material.base.entity.po;

import lombok.Data;

/**
 * @Description 楼盘户型
 * @Author hubin
 * @Date 2020-04-30 10:55
 * @Version 1.0
 **/
@Data
public class BuildingUnitType {
    private Long id = 0L;
    private String type;        // 户型
    private String area;        // 面积
    private String imgId;       // 图片ID
}
