package com.bin.cloud.business.material.api.collect;

import com.bin.cloud.business.material.api.ApiConstant;
import com.bin.cloud.business.material.api.collect.context.CollectInfoContext;
import com.bin.cloud.business.material.base.entity.query.CollectPageQuery;
import com.bin.cloud.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description 收藏
 * @Author hubin
 * @Date 2020-05-19 10:40
 * @Version 1.0
 **/
@RestController
public class CollectApi {

    @Resource
    private CollectInfoContext collectInfoContext;

    @PostMapping(ApiConstant.COLLECT_SAVE)
    public Result collectSave(@RequestBody Map<String, Long> params) {
        return collectInfoContext.collect(params);
    }

    @PostMapping(ApiConstant.COLLECT_PAGE_QUERY)
    public Result page(@RequestBody CollectPageQuery query) {
        return collectInfoContext.page(query.build());
    }

    @PostMapping(ApiConstant.COLLECT_TOTAL_BY_USER_ID)
    public Result getTotal(@RequestBody Map<String, Long> params) {
        return collectInfoContext.queryTotal(params);
    }

}
