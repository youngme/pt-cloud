package com.bin.cloud.business.material.base.entity.po;

import com.bin.cloud.common.core.entity.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

/**
 * @Description 建材信息关系
 * @Author hubin
 * @Date 2019-10-10 14:04
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Alias("jccMaterialRelation")
public class JccMaterialRelation extends BasePo {
    private Long userId;            // 用户ID
    private Long materialId;        // 信息ID
    private Integer type;           // 信息类型
}
