package com.bin.cloud.system.core.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bin.cloud.system.core.base.entity.po.ThreadPartyRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-10-21 17:26
 * @Version 1.0
 **/
@Mapper
public interface ThreadPartyRelationMapper extends BaseMapper<ThreadPartyRelation> {

   ThreadPartyRelation queryInfoByOpenid(String openid);
}
