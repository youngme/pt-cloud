package com.bin.cloud.business.material.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bin.cloud.business.material.base.entity.po.OssFileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description 图片服务
 * @Author hubin
 * @Date 2019-09-18 14:29
 * @Version 1.0
 **/
@Mapper
public interface OssFileInfoMapper extends BaseMapper<OssFileInfo> {

    List<String> queryImgUrlByIds(@Param("ids") List<Long> ids);
}
