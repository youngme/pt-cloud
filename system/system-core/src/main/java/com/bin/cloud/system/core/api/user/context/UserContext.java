package com.bin.cloud.system.core.api.user.context;

import com.bin.cloud.common.core.entity.vo.Result;
import com.bin.cloud.common.core.utils.DataConvert;
import com.bin.cloud.system.core.api.role.context.RoleContext;
import com.bin.cloud.system.core.base.entity.dto.UserInfoDTO;
import com.bin.cloud.system.core.base.entity.po.ThreadPartyRelation;
import com.bin.cloud.system.core.base.entity.po.Users;
import com.bin.cloud.system.core.base.entity.po.UsersRolesRelation;
import com.bin.cloud.system.core.base.entity.vo.UserInfoVo;
import com.bin.cloud.system.core.base.service.RoleRelationService;
import com.bin.cloud.system.core.base.service.ThreadPartyRelationService;
import com.bin.cloud.system.core.base.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Description 用户接口
 * @Author hubin
 * @Date 2019-10-21 17:16
 * @Version 1.0
 **/
@Service
@Slf4j
public class UserContext {

    @Resource
    UserService userService;

    @Resource
    ThreadPartyRelationService threadPartyRelationService;

    @Resource
    RoleRelationService roleRelationService;

    @Resource
    RoleContext roleContext;


    public Result getByUsername(String username) {
        return Result.success(userService.getByUsername(username));
    }

    public Result getByMobile(String mobile) {
        return Result.success(userService.getByMobile(mobile));
    }

    public Result getByOpenid(String openid) {
        return Result.success(userService.getByOpenid(openid));
    }

    public Result saveOrUpdateUserInfo(UserInfoDTO dto) {
        try {
            Users users = userService.saveOrUpdateUserInfo(dto);
            if (Objects.nonNull(users.getId())) {
                threadPartyRelationService.saveOrUpdate(new ThreadPartyRelation(dto.getOpenid(), users.getId()));
            }
            UsersRolesRelation rolesRelation = roleRelationService.getRoleRelationByUserId(users.getId());
            if (Objects.isNull(rolesRelation)) {
                // 登陆用户角色初始化为用户；104标识为用户
//                roleRelationService.save(new UsersRolesRelation(users.getId(), 101L));
                log.info("初始化用户权限");
                roleContext.saveRoleByUid(users.getId());
                log.info("初始化用户权限完成");
            }
            DataConvert.mergeNotNullReflect(users, dto);
            return Result.success(DataConvert.mergeNotNullReflect(dto, UserInfoVo.class));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failMessage("操作失败");
        }
    }
}
