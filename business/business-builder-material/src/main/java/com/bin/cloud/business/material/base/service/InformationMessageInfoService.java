package com.bin.cloud.business.material.base.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.cloud.business.material.base.dao.InformationMessageInfoMapper;
import com.bin.cloud.business.material.base.entity.po.InformationMessageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-04-12 22:13
 * @Version 1.0
 **/
@Service
public class InformationMessageInfoService extends ServiceImpl<InformationMessageInfoMapper, InformationMessageInfo> {

    public List<InformationMessageInfo> queryList(Long pjId) {
        return baseMapper.queryListByPjId(pjId);
    }
}
