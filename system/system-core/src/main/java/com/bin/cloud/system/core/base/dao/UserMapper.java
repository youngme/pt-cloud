package com.bin.cloud.system.core.base.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bin.cloud.common.core.entity.dto.PageParam;
import com.bin.cloud.system.core.base.entity.po.Users;
import com.bin.cloud.system.core.base.entity.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<Users> {

    Users getByUsername(String username);

    Users getByMobile(String mobile);

    List<Users> queryUserPage(PageParam pageParam);

    Long queryPageTotal();

    UserInfoVo getByOpenid(String openid);
}