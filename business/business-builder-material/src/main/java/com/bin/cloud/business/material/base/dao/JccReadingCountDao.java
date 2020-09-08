package com.bin.cloud.business.material.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bin.cloud.business.material.base.entity.po.JccReadingCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-06-24 08:26
 * @Version 1.0
 **/
@Mapper
public interface JccReadingCountDao extends BaseMapper<JccReadingCount> {

    @Update("update jcc_reading_count set count=count+1 where p_id = #{pId} and type= #{type}")
    void updateReadCount(@Param("pId") Long pId, @Param("type") Integer type);

    @Select("select count from jcc_reading_count where  p_id = #{pId} and type= #{type}")
    Integer getReadingCount(@Param("pId") Long pId, @Param("type") Integer type);

    @Select("select * from jcc_reading_count where  p_id = #{pId} and type= #{type}")
    JccReadingCount queryInfo(@Param("pId") Long pId, @Param("type") Integer type);
}
