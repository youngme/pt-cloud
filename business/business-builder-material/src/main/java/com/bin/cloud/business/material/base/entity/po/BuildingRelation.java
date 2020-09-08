package com.bin.cloud.business.material.base.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-04-30 15:55
 * @Version 1.0
 **/
@Data
@Alias("buildingRelation")
public class BuildingRelation {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    private Long propertyId;
    private String proveId;
    private String unitTypeId;
    private Long userId;
    private Date createdTime = Date.from(ZonedDateTime.now().toInstant());
    private Date updatedTime = Date.from(ZonedDateTime.now().toInstant());
}
