package com.bin.cloud.system.core.base.entity.po;

import com.bin.cloud.common.core.entity.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description 角色资源关系
 * @Author hubin
 * @Date 2019-10-18 13:35
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RolesResourcesRelation extends BasePo {
    private Long resourceId;    // 角色id
    private Long roleId;        // 资源id
}
