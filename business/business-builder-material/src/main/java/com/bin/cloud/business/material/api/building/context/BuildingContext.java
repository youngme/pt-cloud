package com.bin.cloud.business.material.api.building.context;

import com.bin.cloud.business.material.api.collect.context.CollectInfoContext;
import com.bin.cloud.business.material.api.readingCount.context.JccReadingCountContext;
import com.bin.cloud.business.material.base.entity.po.*;
import com.bin.cloud.business.material.base.entity.query.BuildingPageQuery;
import com.bin.cloud.business.material.base.entity.query.BuildingQuery;
import com.bin.cloud.business.material.base.entity.vo.BuildingInfoVo;
import com.bin.cloud.business.material.base.entity.vo.BuildingPageVo;
import com.bin.cloud.business.material.base.entity.vo.BuildingProveVo;
import com.bin.cloud.business.material.base.entity.vo.BuildingUnitTypeVo;
import com.bin.cloud.business.material.base.service.*;
import com.bin.cloud.common.core.entity.vo.PageVo;
import com.bin.cloud.common.core.entity.vo.Result;
import com.bin.cloud.common.core.exception.ErrorType;
import com.bin.cloud.common.core.exception.SystemErrorType;
import com.bin.cloud.common.core.utils.DataConvert;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-05-03 12:25
 * @Version 1.0
 **/
@Service
@Transactional
public class BuildingContext {

    @Resource
    private BuildingInfoService infoService;

    @Resource
    private BuildingPropertyService propertyService;

    @Resource
    private BuildingProveService proveService;

    @Resource
    private BuildingUnitTypeService unitTypeService;

    @Resource
    private BuildingRelationService relationService;

    @Resource
    private OssFileInfoService ossFileInfoService;

    @Resource
    private CollectInfoService collectInfoService;

    @Resource
    private JccReadingCountContext jccReadingCountContext;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Result save(BuildingQuery query) {
        BuildingRelation relation = new BuildingRelation();
        relation.setUserId(query.getUserId());
        BuildingInfo info = DataConvert.mergeNotNullReflect(query, BuildingInfo.class);
        assert info != null;
        infoService.save(info);
        relation.setId(info.getId());
        BuildingProperty property = DataConvert.mergeNotNullReflect(query, BuildingProperty.class);
        propertyService.save(property);
        assert property != null;
        relation.setPropertyId(property.getId());
        List<Long> proveIds = query.getProveList().stream().map(prove -> {
            proveService.save(prove);
            return prove.getId();
        }).collect(Collectors.toList());
        relation.setProveId(StringUtils.join(proveIds, ','));
        List<Long> unitTypeIds = query.getUnitTypeList().stream().map(unitType -> {
            unitTypeService.save(unitType);
            return unitType.getId();
        }).collect(Collectors.toList());
        relation.setUnitTypeId(StringUtils.join(unitTypeIds, ','));
        relationService.saveOrUpdate(relation);
        jccReadingCountContext.initReadCount(info.getId(), query.getType());
        return Result.success();
    }

    public Result pageList(BuildingPageQuery query) {
        try {
            PageVo page = infoService.pageList(query);
            List<?> rows = page.getRows();
            List<?> vos = rows.stream().map(vo ->
                    this.initData(vo, query.getType())).collect(Collectors.toList());
            page.setRows(vos);
            return Result.success(page);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failMessage("系统错误");
        }
    }

    public Result queryInfo(Map<String, Long> params) {
        if (params.isEmpty()) {
            return Result.failMessage("参数为空");
        }
        try {
            Long infoId = params.get("id");
            Long userId = params.get("userId");
            BuildingInfo info = infoService.queryInfo(infoId);
            BuildingInfoVo buildingInfoVo = DataConvert.mergeNotNullReflect(info, BuildingInfoVo.class);
            assert buildingInfoVo != null;
            buildingInfoVo.setBuildingImgList(ossFileInfoService.getImgUrl(info.getBuildingImgs()));
            buildingInfoVo.setStImgUrls(ossFileInfoService.getImgUrl(info.getStImgIds()));
            BuildingRelation relation = relationService.queryInfo(infoId);
            assert relation != null;
            BuildingProperty property = propertyService.queryInfo(relation.getPropertyId());
            DataConvert.mergeNotNullReflect(property, buildingInfoVo);
            String []  provIds = relation.getProveId().split(",");
            buildingInfoVo.setProves(new ArrayList<>());
            for (String  id: provIds) {
                BuildingProve prove = proveService.getById(Long.parseLong(id));
                BuildingProveVo vo = new BuildingProveVo();
                vo.setSendTime(prove.getSendTime());
                vo.setImgUrl(ossFileInfoService.getInfo(prove.getImgId()));
                buildingInfoVo.getProves().add(vo);
            }
            String[] unitTypeIds = relation.getUnitTypeId().split(",");
            buildingInfoVo.setUnitTypes(new ArrayList<>());
            for (String id : unitTypeIds) {
                BuildingUnitType unitType = unitTypeService.getById(id);
                BuildingUnitTypeVo vo = new BuildingUnitTypeVo();
                vo.setArea(unitType.getArea());
                vo.setType(unitType.getType());
                vo.setImgUrls(ossFileInfoService.getImgUrl(unitType.getImgId()));
                buildingInfoVo.getUnitTypes().add(vo);
            }
            Long collectId = collectInfoService.queryId(infoId, userId, 2L);
            buildingInfoVo.setCollectId(collectId);
            Integer count = jccReadingCountContext.getCount(buildingInfoVo.getId(), Integer.parseInt(params.get("type").toString()));
            buildingInfoVo.setReadCount(count);
            return Result.success(buildingInfoVo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failMessage("系统错误");
        }
    }

    public Result queryPushTotalByUser(Map<String, Long> params) {
        if (params.isEmpty()) {
            return Result.failMessage("参数为空");
        }
        Long userId = params.get("userId");
        if (Objects.nonNull(userId)) {
            int buildingTotal = relationService.queryTotalByUserId(userId);
            return Result.success((buildingTotal));
        }
        return Result.success(0);
    }

    public Result queryPushListByUser(Map<String, Long> params) {
        if (params.isEmpty()) {
            return Result.failMessage("参数为空");
        }
        Long userId = params.get("userId");
        List<BuildingInfo> infos = infoService.queryPushList(userId);
        if (Objects.nonNull(infos)) {
            List<BuildingPageVo> list = infos.stream().map(this::initData).collect(Collectors.toList());
            return Result.success(list);
        }
        return Result.success();
    }

    private BuildingPageVo initData(Object info) {
        BuildingInfo po = (BuildingInfo) info;
        BuildingPageVo vo = DataConvert.mergeNotNullReflect(info, BuildingPageVo.class);
        assert vo != null;
        vo.setBuildingImgList(ossFileInfoService.getImgUrl(po.getBuildingImgs()));
        vo.setArea(vo.getArea().replace(',', '-'));
        return vo;
    }

    private BuildingPageVo initData(Object info,int type) {
        BuildingPageVo vo = initData(info);
        Integer count = jccReadingCountContext.getCount(vo.getId(), type);
        vo.setReadCount(count);
        return vo;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Result remove(Map<String, Long> params) {
        if (params.isEmpty()) {
            return Result.failMessage("参数为空");
        }
        try {
            Long id = params.get("id");
            BuildingRelation relation = relationService.getById(id);
            if (StringUtils.isNotBlank(relation.getProveId())) {
                String[] proIds = relation.getProveId().split(",");
                for (String proId : proIds) {
                    proveService.removeById(Long.parseLong(proId));
                }
            }
            if (StringUtils.isNotBlank(relation.getUnitTypeId())) {
                String[] proIds = relation.getUnitTypeId().split(",");
                for (String proId : proIds) {
                    unitTypeService.removeById(Long.parseLong(proId));
                }
            }
            propertyService.removeById(relation.getPropertyId());
            infoService.removeById(id);
            relationService.removeById(id);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail(SystemErrorType.SYSTEM_ERROR);
        }
    }
}
