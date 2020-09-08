package com.bin.cloud.business.material.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bin.cloud.business.material.base.entity.po.BuildingRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-04-30 15:59
 * @Version 1.0
 **/
@Mapper
public interface BuildingRelationMapper extends BaseMapper<BuildingRelation> {

    BuildingRelation queryInfo(Long id);

    List<BuildingRelation> queryList();

    @Select("select count(1) from building_relation where user_id = #{userId}")
    int queryTotalByUserId(@Param("userId") Long userId);
}
