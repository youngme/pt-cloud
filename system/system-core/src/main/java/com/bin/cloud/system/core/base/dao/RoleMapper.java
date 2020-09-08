package com.bin.cloud.system.core.base.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bin.cloud.common.core.entity.dto.PageParam;
import com.bin.cloud.system.core.base.entity.po.Roles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;


@Mapper
public interface RoleMapper extends BaseMapper<Roles> {

    Set<Roles> queryByUserId(long userId);

    Set<Roles> queryRoleList();

    List<Roles> queryRolePage(PageParam pageParam);

    Long queryPageTotal();

    @Select("select * from roles where code = #{code}")
    Roles queryRoleByCode(@Param("code") String code);
}