package com.bin.cloud.business.material.base.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.cloud.business.material.base.dao.JccReadingCountDao;
import com.bin.cloud.business.material.base.entity.po.JccReadingCount;
import org.springframework.stereotype.Service;

/**
 * @Description 阅读量统计
 * @Author hubin
 * @Date 2020-06-24 08:25
 * @Version 1.0
 **/
@Service
public class JccReadingCountService extends ServiceImpl<JccReadingCountDao, JccReadingCount> {

    public JccReadingCount queryInfo(Long pId, Integer type) {
        return baseMapper.queryInfo(pId, type);
    }

   public void updateCount(Long pId, Integer type) {
        baseMapper.updateReadCount(pId, type);
    }

   public Integer getReadingCount(Long pId, Integer type) {
        return baseMapper.getReadingCount(pId, type);
    }
}
