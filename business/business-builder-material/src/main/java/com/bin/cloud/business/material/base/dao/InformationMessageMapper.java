package com.bin.cloud.business.material.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bin.cloud.business.material.base.entity.po.InformationMessage;
import com.bin.cloud.business.material.base.entity.query.InformationMessagePageQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 实时资讯
 * @Author hubin
 * @Date 2020-04-12 10:05
 * @Version 1.0
 **/
@Mapper
public interface InformationMessageMapper extends BaseMapper<InformationMessage> {
    List<InformationMessage> queryPage(@Param("pageParams") InformationMessagePageQuery pageParams);

    Long queryPageTotal(@Param("type") String type);
}
