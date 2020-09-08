package com.bin.cloud.business.material.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bin.cloud.business.material.base.entity.po.JccMaterialRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description
 * @Author hubin
 * @Date 2019-10-10 15:04
 * @Version 1.0
 **/
@Mapper
public interface JccMaterialRelationMapper extends BaseMapper<JccMaterialRelation> {

    @Select("select count(1) from jcc_material_relation where user_id = #{userId}")
    int queryTotalByUserId(@Param("userId") Long userId);

    @Select("select id from jcc_material_relation where material_id =#{materialId}")
    Long queryIdByInfoId(@Param("materialId") Long materialId);
}
