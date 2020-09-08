package com.bin.cloud.business.material.api.collect.context;

import com.bin.cloud.business.material.base.entity.po.BuildingInfo;
import com.bin.cloud.business.material.base.entity.po.CollectInfo;
import com.bin.cloud.business.material.base.entity.query.CollectPageQuery;
import com.bin.cloud.business.material.base.entity.vo.BuildingPageVo;
import com.bin.cloud.business.material.base.entity.vo.JccMaterialInfoVo;
import com.bin.cloud.business.material.base.entity.vo.JccMaterialVo;
import com.bin.cloud.business.material.base.service.BuildingInfoService;
import com.bin.cloud.business.material.base.service.CollectInfoService;
import com.bin.cloud.business.material.base.service.JccMaterialInfoService;
import com.bin.cloud.business.material.base.service.OssFileInfoService;
import com.bin.cloud.common.core.entity.vo.PageVo;
import com.bin.cloud.common.core.entity.vo.Result;
import com.bin.cloud.common.core.utils.DataConvert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-05-19 10:33
 * @Version 1.0
 **/
@Service
public class CollectInfoContext {

    @Resource
    private CollectInfoService collectInfoService;

    @Resource
    private JccMaterialInfoService jccMaterialInfoService;

    @Resource
    private OssFileInfoService ossFileInfoService;

    @Resource
    private BuildingInfoService buildingInfoService;

    public Result collect(Map<String, Long> params) {
        if (params.isEmpty()) {
            return Result.failMessage("收藏失败,请重新登录");
        }
        Long userId = params.get("userId");
        Long pId = params.get("pId");
        Long type = params.get("type");
        if (Objects.isNull(userId) && Objects.isNull(pId) && Objects.isNull(type)) {
            return Result.failMessage("收藏失败,请重新登录");
        }
        Long id = collectInfoService.queryId(pId, userId, type);
        if (Objects.isNull(id)) {
            CollectInfo collectInfo = new CollectInfo();
            collectInfo.setPId(pId);
            collectInfo.setType(Integer.valueOf(String.valueOf(type)));
            collectInfo.setUserId(userId);
            collectInfoService.save(collectInfo);
        }else{
            collectInfoService.removeById(id);
        }
        return Result.success();
    }

    public Result queryTotal(Map<String, Long> params) {
        if (params.isEmpty()) {
            return Result.failMessage("暂无用户信息");
        }
        return Result.success(collectInfoService.queryCollectTotal(params.get("userId")));
    }

    public Result page(CollectPageQuery query) {
        List<CollectInfo> collectInfos = collectInfoService.queryPageList(query);
        Long total = collectInfoService.queryCollectTotal(query.getUserId());
        List<Object> objects = collectInfos.stream().map(collectInfo -> {
            Object obj;
            if (collectInfo.getType() == 1) {
                JccMaterialInfoVo jccMaterialInfoVo = jccMaterialInfoService.queryInfoById(collectInfo.getPId());
                JccMaterialVo vo = DataConvert.mergeNotNullReflect(jccMaterialInfoVo, JccMaterialVo.class);
                assert vo != null;
                vo.setImgUrls(ossFileInfoService.getImgUrl(jccMaterialInfoVo.getImgId()));
                vo.setId(collectInfo.getPId());
                obj = vo;
            } else {
                BuildingInfo po = buildingInfoService.queryInfo(collectInfo.getPId());
                BuildingPageVo vo = DataConvert.mergeNotNullReflect(po, BuildingPageVo.class);
                assert vo != null;
                vo.setBuildingImgList(ossFileInfoService.getImgUrl(po.getBuildingImgs()));
                vo.setArea(vo.getArea().replace(',', '-'));
                obj = vo;
            }
            return obj;
        }).collect(Collectors.toList());
        PageVo pageVo = new PageVo();
        pageVo.setRows(objects);
        pageVo.setTotal(total);
        return Result.success(pageVo);
    }

}
