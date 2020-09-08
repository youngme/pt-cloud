package com.bin.cloud.business.material.base.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.cloud.business.material.base.dao.JccMaterialInfoMapper;
import com.bin.cloud.business.material.base.entity.dto.JccMaterialListDTO;
import com.bin.cloud.business.material.base.entity.po.JccMaterialInfo;
import com.bin.cloud.business.material.base.entity.query.JccMaterialPageQuery;
import com.bin.cloud.business.material.base.entity.vo.JccMaterialInfoVo;
import com.bin.cloud.common.core.entity.vo.PageVo;
import com.bin.cloud.common.core.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 建材信息
 * @Author hubin
 * @Date 2019-09-11 13:56
 * @Version 1.0
 **/
@Service
@Transactional
@Slf4j
public class JccMaterialInfoService extends ServiceImpl<JccMaterialInfoMapper, JccMaterialInfo> {

    public PageVo queryPageInfo(JccMaterialPageQuery pageQuery) throws Exception {
        try {
            List<JccMaterialInfoVo> list = baseMapper.queryPage(pageQuery.build());
            Long total = baseMapper.queryPageTotal(pageQuery.getType());
            return PageVo.build().rows(list).total(total);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public List<JccMaterialInfoVo> queryListByTypeList() {
        return baseMapper.queryListByTypeList();
    }

    public List<JccMaterialInfoVo> queryListByUserId(Long userId) {
        return baseMapper.queryListByUserId(userId);
    }

    public JccMaterialInfoVo queryInfoById(Long id) {
        return baseMapper.queryInfoById(id);
    }
}
