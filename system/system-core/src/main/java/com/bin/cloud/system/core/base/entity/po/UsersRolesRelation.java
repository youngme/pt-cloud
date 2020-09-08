package com.bin.cloud.system.core.base.entity.po;

import com.bin.cloud.common.core.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description 用户角色关系
 * @Author hubin
 * @Date 2019-10-18 13:36
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UsersRolesRelation extends BasePo {

    private Long userId;        // 用户ID
    private Long roleId;        // 角色ID
}
