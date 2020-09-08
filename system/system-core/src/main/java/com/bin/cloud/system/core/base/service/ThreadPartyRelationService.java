package com.bin.cloud.system.core.base.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.cloud.common.core.utils.DataConvert;
import com.bin.cloud.system.core.base.dao.ThreadPartyRelationMapper;
import com.bin.cloud.system.core.base.entity.po.ThreadPartyRelation;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-10-21 17:24
 * @Version 1.0
 **/
@Service
public class ThreadPartyRelationService extends ServiceImpl<ThreadPartyRelationMapper, ThreadPartyRelation> {


    public boolean saveOrUpdate(ThreadPartyRelation threadPartyRelation) {
        ThreadPartyRelation queryData = baseMapper.queryInfoByOpenid(threadPartyRelation.getOpenid());
        try {
            if (Objects.nonNull(queryData)) {
                DataConvert.mergeNotNullReflect(threadPartyRelation, queryData);
                baseMapper.updateById(queryData);
            } else {
                baseMapper.insert(threadPartyRelation);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
