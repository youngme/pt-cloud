package com.bin.cloud.system.core.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bin.cloud.system.core.base.entity.po.UserVipInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description 会员信息数据接口
 * @Author hubin
 * @Date 2019-10-18 14:14
 * @Version 1.0
 **/
@Mapper
public interface UserVipInfoMapper extends BaseMapper<UserVipInfo> {

    UserVipInfo getVipInfoByUserId(Long userId);
}
