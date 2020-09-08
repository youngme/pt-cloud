package com.bin.cloud.business.material.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bin.cloud.business.material.base.entity.po.BuildingInfo;
import com.bin.cloud.business.material.base.entity.query.BuildingPageQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-04-30 11:48
 * @Version 1.0
 **/
@Mapper
public interface BuildingInfoMapper extends BaseMapper<BuildingInfo> {

    BuildingInfo queryInfo(Long id);

    Long queryPageTotal();

    List<BuildingInfo> queryPage(@Param("buildingPageQuery") BuildingPageQuery query);

    @Select("select t.* from building_relation r,building_info t " +
            "where t.id = r.id and r.user_id = #{userId} order by created_time desc")
    List<BuildingInfo> queryPushList(@Param("userId") Long userId);
}
