package com.bin.cloud.system.core.base.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.cloud.common.core.entity.dto.PageParam;
import com.bin.cloud.common.core.entity.vo.PageVo;
import com.bin.cloud.common.core.entity.vo.Result;
import com.bin.cloud.common.core.utils.DataConvert;
import com.bin.cloud.system.core.base.dao.UserMapper;
import com.bin.cloud.system.core.base.entity.dto.UserInfoDTO;
import com.bin.cloud.system.core.base.entity.po.Users;
import com.bin.cloud.system.core.base.entity.vo.UserInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


@Service
public class UserService extends ServiceImpl<UserMapper, Users>{


    public Users getByUsername(String username) {
        return baseMapper.getByUsername(username);
    }


    public Users getByMobile(String mobile) {
        return baseMapper.getByMobile(mobile);
    }


    public UserInfoVo getByOpenid(String openid) {
        UserInfoVo vo = baseMapper.getByOpenid(openid);
        return vo;
    }


    public Users saveOrUpdateUserInfo(UserInfoDTO userInfo) throws Exception {
        try {
            Users users = DataConvert.mergeNotNullReflect(userInfo, Users.class);
            users.setId(userInfo.getId());
            if (Objects.nonNull(users.getId()) && users.getId() > 0) {
                baseMapper.updateById(users);
            } else {
                baseMapper.insert(users);
            }
            return users;
        } catch (Exception e) {
            throw new Exception();
        }
    }


    public PageVo queryUserPage(PageParam pageParam) {
        List<Users> list = baseMapper.queryUserPage(pageParam.build());
        Long total = baseMapper.queryPageTotal();
        return PageVo.build().rows(list).total(total);
    }


    public Result saveUser(Users users) {
        return Result.success(baseMapper.insert(users));
    }

}
