package com.bin.cloud.business.material.base.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.cloud.business.material.base.dao.InformationMessageMapper;
import com.bin.cloud.business.material.base.entity.po.InformationMessage;
import com.bin.cloud.business.material.base.entity.query.InformationMessagePageQuery;
import com.bin.cloud.common.core.entity.vo.PageVo;
import com.bin.cloud.common.core.entity.vo.Result;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-04-12 10:25
 * @Version 1.0
 **/
@Service
public class InformationMessageService extends ServiceImpl<InformationMessageMapper, InformationMessage> {

    public PageVo queryPageInfo(InformationMessagePageQuery pageQuery) throws Exception {
        try {
            List<InformationMessage> list = baseMapper.queryPage(pageQuery.build());
            Long total = baseMapper.queryPageTotal(pageQuery.getType());
            return PageVo.build().rows(list).total(total);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void addReadCount(Long id) {
        InformationMessage informationMessage = baseMapper.selectById(id);
        if (Objects.nonNull(informationMessage)) {
            informationMessage.setReadCount(informationMessage.getReadCount() + 1);
            saveOrUpdate(informationMessage);
        }else{
           throw new RuntimeException("查无此ID信息");
        }

    }
}
