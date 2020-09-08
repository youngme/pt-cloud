package com.bin.cloud.business.material.base.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.cloud.business.material.base.dao.JccMaterialRelationMapper;
import com.bin.cloud.business.material.base.entity.po.JccMaterialRelation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-10-10 15:03
 * @Version 1.0
 **/
@Service
@Transactional
public class JccMaterialRelationService extends ServiceImpl<JccMaterialRelationMapper, JccMaterialRelation> {

    public int queryTotalByUserId(Long userId) {
        return baseMapper.queryTotalByUserId(userId);
    }

    public Long queryIdByInfoId(Long materialId) {
        return baseMapper.queryIdByInfoId(materialId);
    }
}
