package com.bin.cloud.business.material.base.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.cloud.business.material.base.dao.BuildingRelationMapper;
import com.bin.cloud.business.material.base.entity.po.BuildingRelation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-04-30 16:30
 * @Version 1.0
 **/
@Service
public class BuildingRelationService extends ServiceImpl<BuildingRelationMapper, BuildingRelation> {

    public BuildingRelation queryInfo(Long id) {
        return baseMapper.selectById(id);
    }

    public List<BuildingRelation> queryList() {
        return baseMapper.queryList();
    }

    public int queryTotalByUserId(Long userId) {
        return baseMapper.queryTotalByUserId(userId);
    }
}
