package com.bin.cloud.business.material.api.building;

import com.bin.cloud.business.material.api.ApiConstant;
import com.bin.cloud.business.material.api.building.context.BuildingContext;
import com.bin.cloud.business.material.api.readingCount.context.JccReadingCountContext;
import com.bin.cloud.business.material.base.entity.query.BuildingPageQuery;
import com.bin.cloud.business.material.base.entity.query.BuildingQuery;
import com.bin.cloud.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-05-03 12:26
 * @Version 1.0
 **/
@RestController
public class BuildingApi {

    @Resource
    private BuildingContext context;


    /**
     * 根据类型获取列表数据
     *
     * @return
     */
    @PostMapping(ApiConstant.BUILDING_INFO_SAVE)
    public Result save(@RequestBody BuildingQuery query) {
        return context.save(query);
    }

    @PostMapping(ApiConstant.BUILDING_PAGE_LIST)
    public Result page(@RequestBody BuildingPageQuery query) {
        return context.pageList(query);
    }

    @PostMapping(ApiConstant.BUILDING_QUERY_INFO)
    public Result queryInfo(@RequestBody Map<String, Long> params) {
        return context.queryInfo(params);
    }

    @PostMapping(ApiConstant.BUILDING_QUERY_PUSH_LIST_BY_USER)
    public Result queryPushListByUser(@RequestBody Map<String, Long> params) {
        return context.queryPushListByUser(params);
    }

    @PostMapping(ApiConstant.QUERY_BUILDING_PUSH_TOTAL_BY_USER_ID)
    public Result queryPushTotalByUser(@RequestBody Map<String, Long> params) {
        return context.queryPushTotalByUser(params);
    }

    @PostMapping(ApiConstant.JCC_BUILDING_REMOVE_BY_ID)
    public Result remove(@RequestBody Map<String, Long> params) {
        return context.remove(params);
    }

}
