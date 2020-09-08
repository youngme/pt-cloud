package com.bin.cloud.system.core.base.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.cloud.system.core.base.dao.RoleRelationMapper;
import com.bin.cloud.system.core.base.entity.po.UsersRolesRelation;
import org.springframework.stereotype.Service;


/**
 * @Description 角色用户关系
 * @Author hubin
 * @Date 2019-11-05 15:58
 * @Version 1.0
 **/
@Service
public class RoleRelationService extends ServiceImpl<RoleRelationMapper, UsersRolesRelation>{

    public UsersRolesRelation getRoleRelationByUserId(Long uid) {
        return baseMapper.queryInfoByUserId(uid);
    }
}
