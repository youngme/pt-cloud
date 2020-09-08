package com.bin.cloud.business.material.api.readingCount;

import com.bin.cloud.business.material.api.ApiConstant;
import com.bin.cloud.business.material.api.readingCount.context.JccReadingCountContext;
import com.bin.cloud.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-06-24 08:40
 * @Version 1.0
 **/
@RestController
public class JccReadingCountApi {

    @Resource
    private JccReadingCountContext context;

    /**
     * 根据帖子ID更新阅读量
     * @param params
     * @return
     */
    @PostMapping(ApiConstant.JCC_UPDATE_READING_COUNT)
    public Result updateCount(@RequestBody Map<String,Object> params) {
        if (params.isEmpty()) {
            return Result.failMessage("参数为空");
        }
        return context.updateCount(params);
    }


    /**
     * 根据帖子ID获取阅读量
     * @param params
     * @return
     */
    @PostMapping(ApiConstant.JCC_GET_READING_COUNT)
    public Result getCount(@RequestBody Map<String,Object> params) {
        if (params.isEmpty()) {
            return Result.failMessage("参数为空");
        }
        return context.getCount(params);
    }

}
