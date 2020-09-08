package com.bin.cloud.business.material.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bin.cloud.business.material.base.entity.po.InformationMessageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-04-12 22:06
 * @Version 1.0
 **/
@Mapper
public interface InformationMessageInfoMapper extends BaseMapper<InformationMessageInfo> {

    @Select("select * from information_message_info where pj_id = #{pjId} order by id ASC")
    List<InformationMessageInfo> queryListByPjId(@Param("pjId") Long pjId);
}
