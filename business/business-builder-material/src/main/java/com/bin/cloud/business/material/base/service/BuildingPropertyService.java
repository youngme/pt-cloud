package com.bin.cloud.business.material.base.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.cloud.business.material.base.dao.BuildingPropertyMapper;
import com.bin.cloud.business.material.base.entity.po.BuildingProperty;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-04-30 16:31
 * @Version 1.0
 **/
@Service
public class BuildingPropertyService extends ServiceImpl<BuildingPropertyMapper,BuildingProperty> {

    public BuildingProperty queryInfo(Long id) {
        return baseMapper.selectById(id);
    }
}
