package com.bin.cloud.business.material.base.entity.po;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @Description 楼盘证书
 * @Author hubin
 * @Date 2020-04-30 10:55
 * @Version 1.0
 **/
@Data
@Alias("buildingProve")
public class BuildingProve {

    private Long id = 0L;
    private Long imgId;           // 证书图片ID集合
    private Date sendTime = Date.from(ZonedDateTime.now().toInstant());     // 发证日期
}
