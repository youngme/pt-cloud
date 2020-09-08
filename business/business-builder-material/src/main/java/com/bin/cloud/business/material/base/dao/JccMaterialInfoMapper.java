package com.bin.cloud.business.material.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bin.cloud.business.material.base.entity.dto.JccMaterialListDTO;
import com.bin.cloud.business.material.base.entity.po.JccMaterialInfo;
import com.bin.cloud.business.material.base.entity.query.JccMaterialPageQuery;
import com.bin.cloud.business.material.base.entity.vo.JccMaterialInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-09-11 13:53
 * @Version 1.0
 **/
@Mapper
public interface JccMaterialInfoMapper extends BaseMapper<JccMaterialInfo> {

   List<JccMaterialInfoVo> queryPage(@Param("jccMaterialPageQuery")JccMaterialPageQuery jccMaterialPageQuery);

   Long queryPageTotal(@Param("type") String type);

   List<JccMaterialInfoVo> queryListByTypeList();

   List<JccMaterialInfoVo> queryListByUserId(@Param("userId") Long userId);

   JccMaterialInfoVo queryInfoById(@Param("id") Long id);
}
