package com.bin.cloud.business.material.base.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-05-11 17:04
 * @Version 1.0
 **/
@Data
public class BuildingUnitTypeVo {
    private String type;                // 户型
    private String area;                // 面积
    private List<String> imgUrls;       // 图片ID
}
