package com.bin.cloud.business.material.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bin.cloud.business.material.base.entity.po.CollectInfo;
import com.bin.cloud.business.material.base.entity.query.CollectPageQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-05-19 10:07
 * @Version 1.0
 **/
@Mapper
public interface CollectInfoMapper extends BaseMapper<CollectInfo> {

    @Select("select * from collect_info where user_id = #{userId}")
   List<CollectInfo> queryListByUserId(@Param("userId") Long userId);

    @Select("select * from collect_info where user_id = #{pageQuery.userId}" +
            " order by created_time desc limit #{pageQuery.page},#{pageQuery.limit}")
    List<CollectInfo> queryPageByUserId(@Param("pageQuery") CollectPageQuery query);

    @Select("select count(1) from collect_info where user_id = #{userId}")
    Long queryCollectTotal(@Param("userId") Long userId);

    @Select("select id from collect_info where user_id = #{userId} and  p_id = #{pId} and type=#{type}")
    Long queryId(@Param("pId") Long pId, @Param("userId") Long userId,@Param("type") Long type);
}
