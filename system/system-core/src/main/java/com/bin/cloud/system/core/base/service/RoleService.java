package com.bin.cloud.system.core.base.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.cloud.common.core.entity.dto.PageParam;
import com.bin.cloud.common.core.entity.vo.PageVo;
import com.bin.cloud.system.core.base.dao.RoleMapper;
import com.bin.cloud.system.core.base.entity.po.Roles;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService  extends ServiceImpl<RoleMapper, Roles> {


    public Set<Roles> queryUserRolesByUserId(Long userId) {
        return baseMapper.queryByUserId(userId);
    }


    public Set<Roles> queryRoleList() {
        return baseMapper.queryRoleList();
    }


    public PageVo queryRolePage(PageParam pageParam) {
        List<Roles> list = baseMapper.queryRolePage(pageParam.build());
        Long total = baseMapper.queryPageTotal();
        return PageVo.build().rows(list).total(total);
    }

    public Roles queryRoleByCode(String code) {
        return baseMapper.queryRoleByCode(code);
    }

}
