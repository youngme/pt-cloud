package com.bin.cloud.business.material.api.jccMaterial.context;

import com.bin.cloud.business.material.api.collect.context.CollectInfoContext;
import com.bin.cloud.business.material.api.readingCount.context.JccReadingCountContext;
import com.bin.cloud.business.material.base.entity.dto.JccMaterialInfoDTO;
import com.bin.cloud.business.material.base.entity.po.JccMaterialInfo;
import com.bin.cloud.business.material.base.entity.po.JccMaterialRelation;
import com.bin.cloud.business.material.base.entity.query.JccMaterialInfoQuery;
import com.bin.cloud.business.material.base.entity.query.JccMaterialPageQuery;
import com.bin.cloud.business.material.base.entity.vo.JccMaterialInfoVo;
import com.bin.cloud.business.material.base.entity.vo.JccMaterialVo;
import com.bin.cloud.business.material.base.service.CollectInfoService;
import com.bin.cloud.business.material.base.service.JccMaterialInfoService;
import com.bin.cloud.business.material.base.service.JccMaterialRelationService;
import com.bin.cloud.business.material.base.service.OssFileInfoService;
import com.bin.cloud.common.core.entity.vo.PageVo;
import com.bin.cloud.common.core.entity.vo.Result;
import com.bin.cloud.common.core.exception.SystemErrorType;
import com.bin.cloud.common.core.utils.DataConvert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description 信息上下文
 * @Author hubin
 * @Date 2019-10-10 14:56
 * @Version 1.0
 **/
@Service
@Transactional
@Slf4j
public class JccMaterialContext {

    @Resource
    private JccMaterialInfoService jccMaterialInfoService;

    @Resource
    private JccMaterialRelationService jccMaterialRelationService;

    @Resource
    private OssFileInfoService ossFileInfoService;

    @Resource
    private JccReadingCountContext jccReadingCountContext;


    @Resource
    private CollectInfoService collectInfoService;
    /**
     * 信息信息
     *
     * @param query
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Result save(JccMaterialInfoQuery query) {
        JccMaterialInfo info = DataConvert.mergeNotNullReflect(query, JccMaterialInfo.class);
        try {
            assert info != null;
            jccMaterialInfoService.save(info);
            JccMaterialRelation relation = DataConvert.mergeNotNullReflect(query, JccMaterialRelation.class);
            assert relation != null;
            relation.setMaterialId(info.getId());
            jccMaterialRelationService.save(relation);
            jccReadingCountContext.initReadCount(info.getId(), query.getType());
            return Result.success("发布成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("信息新增失败，失败原因:{}", e.getMessage());
            return Result.failMessage("发布失败");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Result update(JccMaterialInfoDTO dto) {
        JccMaterialRelation relation = DataConvert.mergeNotNullReflect(dto, JccMaterialRelation.class);
        assert relation != null;
        relation.setUpdatedTime(Date.from(ZonedDateTime.now().toInstant()));    // 更新时间
        relation.setId(dto.getRelationId());
        JccMaterialInfo info = DataConvert.mergeNotNullReflect(dto, JccMaterialInfo.class);
        assert info != null;
        info.setUpdatedTime(Date.from(ZonedDateTime.now().toInstant()));    // 更新时间
        info.setId(dto.getMaterialId());
        try {
            jccMaterialRelationService.updateById(relation);
            jccMaterialInfoService.updateById(info);
            return Result.success("信息更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("信息更新失败，失败原因:{}", e.getMessage());
            return Result.failMessage("信息更新失败");
        }
    }

    /**
     * 传入关系表的ID
     * @param params
     * @return
     */
    public Result queryInfoById(Map<String,Long> params) {
        if (params.isEmpty()) {
            return Result.failMessage("参数为空");
        }
        Long id = params.get("id");
        Long userId = params.get("userId");
        JccMaterialInfoVo info = jccMaterialInfoService.queryInfoById(id);
        if (Objects.nonNull(info)) {
            JccMaterialVo vo = DataConvert.mergeNotNullReflect(info, JccMaterialVo.class);
            assert vo != null;
            Long collectId = collectInfoService.queryId(id, userId, 1L);
            vo.setCollectId(collectId);
            vo.setImgUrls(ossFileInfoService.getImgUrl(info.getImgId()));
            Integer count = jccReadingCountContext.getCount(id, vo.getType());
            vo.setReadCount(count);
            return Result.success(vo);
        }
        return Result.failMessage("获取信息失败");
    }

    public Result queryPage(JccMaterialPageQuery pageQuery) {
        try {
            PageVo pageVo = jccMaterialInfoService.queryPageInfo(pageQuery);
            List<JccMaterialVo> listVos = pageVo.getRows().stream().map(this::apply).collect(Collectors.toList());
            pageVo.setRows(listVos);
            return Result.success(pageVo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("分页异常，异常信息：{}", e.getMessage());
            return Result.failMessage("分页异常");
        }
    }

    /**
     * 根据类型获取列表
     * @param
     * @return
     */
    public Result queryListByType() {
        List<JccMaterialInfoVo> jccMaterialInfoList = jccMaterialInfoService.queryListByTypeList();
        return Result.success(jccMaterialInfoList.stream().map(this::apply).collect(Collectors.toList()));
    }

    /**
     * 根据用户ID获取当前用户提交过的数据
     *
     * @param userId
     * @return
     */
    public Result queryListByUserId(Long userId) {
        List<JccMaterialVo> listVos = jccMaterialInfoService.queryListByUserId(userId)
                .stream().map(this::apply).collect(Collectors.toList());
        return Result.success(listVos);
    }

    private <T> JccMaterialVo apply(T bo) {
        JccMaterialInfoVo vo = (JccMaterialInfoVo) bo;
        JccMaterialVo jccMaterialVo = DataConvert
                .mergeNotNullReflect(vo, JccMaterialVo.class);
        assert jccMaterialVo != null;
        jccMaterialVo.setId(vo.getMaterialId());
        jccMaterialVo.setImgUrls(ossFileInfoService.getImgUrl(vo.getImgId()));
        // 加入浏览量
        Integer count = jccReadingCountContext.getCount(jccMaterialVo.getId(), vo.getType());
        jccMaterialVo.setReadCount(count);
        return jccMaterialVo;
    }

    public Result queryPushTotalByUser(Map<String, Long> params) {
        if (params.isEmpty()) {
            return Result.failMessage("参数为空");
        }
        Long userId = params.get("userId");
        if (Objects.nonNull(userId)) {
            int total = jccMaterialRelationService.queryTotalByUserId(userId);
            return Result.success(total);
        }
        return Result.success(0);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Result remove(Map<String, Long> params) {
        if (params.isEmpty()) {
            return Result.failMessage("参数为空");
        }
        try {
            Long id = params.get("id");
            jccMaterialRelationService.removeById(jccMaterialRelationService.queryIdByInfoId(id));
            jccMaterialInfoService.removeById(id);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(SystemErrorType.SYSTEM_ERROR);
        }
    }
}
