package com.bin.cloud.auth.authentication.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bin.cloud.auth.authentication.entity.po.Resources;
import com.bin.cloud.common.core.entity.dto.PageParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface ResourceMapper extends BaseMapper<Resources> {

    Set<Resources> findAll();


    Set<Resources> queryByRoleCodes(@Param("roleCodes") String[] roleCodes);

    List<Resources> queryResourcePage(PageParam pageParam);

    Long queryPageTotal();

    Set<Resources> queryByUsername(@Param("username") String username);
}