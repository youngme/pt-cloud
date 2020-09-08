package com.bin.cloud.business.material.base.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.cloud.business.material.base.dao.CollectInfoMapper;
import com.bin.cloud.business.material.base.entity.po.CollectInfo;
import com.bin.cloud.business.material.base.entity.query.CollectPageQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-05-19 10:15
 * @Version 1.0
 **/
@Service
public class CollectInfoService extends ServiceImpl<CollectInfoMapper,CollectInfo> {

   List<CollectInfo> queryListByUserId(Long userId) {
       return baseMapper.queryListByUserId(userId);
    }

    public List<CollectInfo> queryPageList(CollectPageQuery query) {
       return baseMapper.queryPageByUserId(query);
    }

    public Long queryCollectTotal(Long userId) {
        return baseMapper.queryCollectTotal(userId);
    }

    public Long queryId(Long pId, Long userId, Long type) {
        return baseMapper.queryId(pId, userId, type);
    }
}
