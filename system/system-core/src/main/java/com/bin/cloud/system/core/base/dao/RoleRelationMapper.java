package com.bin.cloud.system.core.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bin.cloud.system.core.base.entity.po.UsersRolesRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-11-05 15:59
 * @Version 1.0
 **/

@Mapper
public interface RoleRelationMapper extends BaseMapper<UsersRolesRelation> {

    @Select("select * from users_roles_relation where user_id = #{uid}")
    UsersRolesRelation queryInfoByUserId(@Param("uid") Long uid);
}
