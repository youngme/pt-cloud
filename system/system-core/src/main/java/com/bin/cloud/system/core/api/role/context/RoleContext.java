package com.bin.cloud.system.core.api.role.context;

import com.bin.cloud.common.core.entity.vo.Result;
import com.bin.cloud.system.core.base.entity.po.Roles;
import com.bin.cloud.system.core.base.entity.po.UsersRolesRelation;
import com.bin.cloud.system.core.base.service.RoleRelationService;
import com.bin.cloud.system.core.base.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @Description 用户角色服务
 * @Author hubin
 * @Date 2019-10-21 17:47
 * @Version 1.0
 **/
@Service
public class RoleContext {

    @Resource
    RoleService roleService;
    @Resource
    RoleRelationService roleRelationService;

    public Result queryUserRolesByUserId(Long userId) {
        Set<Roles> rolesSet = roleService.queryUserRolesByUserId(userId);
        return Result.success(rolesSet);
    }

    public Result saveRoleByUid(Long userId) {
        String code = "ADMIN";
        Roles roles = roleService.queryRoleByCode(code);
        if (Objects.nonNull(userId)) {
            UsersRolesRelation po = new UsersRolesRelation();
            po.setUserId(userId);
            po.setRoleId(roles.getId());
            roleRelationService.save(po);
            return Result.success();
        }
        return Result.failMessage("userId为空");
    }
}
