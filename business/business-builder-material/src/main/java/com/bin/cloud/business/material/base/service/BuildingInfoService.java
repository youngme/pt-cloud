package com.bin.cloud.business.material.base.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.cloud.business.material.base.dao.BuildingInfoMapper;
import com.bin.cloud.business.material.base.entity.po.BuildingInfo;
import com.bin.cloud.business.material.base.entity.query.BuildingPageQuery;
import com.bin.cloud.common.core.entity.vo.PageVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-04-30 14:56
 * @Version 1.0
 **/
@Service
public class BuildingInfoService extends ServiceImpl<BuildingInfoMapper, BuildingInfo> {

    public BuildingInfo queryInfo(Long id) {
        return baseMapper.selectById(id);
    }

    public PageVo pageList(BuildingPageQuery query) throws Exception {
        try {
            List<BuildingInfo> list = baseMapper.queryPage(query.build());
            Long total = baseMapper.queryPageTotal();
            return PageVo.build().rows(list).total(total);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<BuildingInfo> queryPushList(Long userId) {
        return baseMapper.queryPushList(userId);
    }
}
