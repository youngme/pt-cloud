package com.bin.cloud.business.material.base.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.cloud.business.material.base.dao.OssFileInfoMapper;
import com.bin.cloud.business.material.base.entity.po.OssFileInfo;
import com.bin.cloud.common.core.entity.dto.ImgDTO;
import com.bin.cloud.common.core.entity.vo.Result;
import com.bin.cloud.common.core.utils.DataConvert;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-09-18 14:31
 * @Version 1.0
 **/
@Service
@Transactional
public class OssFileInfoService extends ServiceImpl<OssFileInfoMapper,OssFileInfo> {

    public Result save(ImgDTO dto) {
        OssFileInfo entity = DataConvert.mergeNotNullReflect(dto, OssFileInfo.class);
        super.save(entity);
        return Result.success(entity);
    }

    public List<String> getImgUrl(String imgIdsStr) {
        if (StringUtils.isNotBlank(imgIdsStr)) {
            List<Long> imgIds = Arrays.stream(imgIdsStr.split(",")).map(Long::parseLong).collect(Collectors.toList());
            List<String> imgUrls = queryImgUrlByIds(imgIds);
            return imgUrls;
        }
        return new ArrayList<>();
    }

    public String getInfo(Long id) {
        OssFileInfo ossFileInfo = baseMapper.selectById(id);
        assert ossFileInfo != null;
        return ossFileInfo.getImgUrl();
    }


    public List<String> queryImgUrlByIds(List<Long> ids) {
        return baseMapper.queryImgUrlByIds(ids);
    }
}
