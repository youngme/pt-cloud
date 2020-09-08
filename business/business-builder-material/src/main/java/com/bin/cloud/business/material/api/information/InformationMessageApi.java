package com.bin.cloud.business.material.api.information;

import com.bin.cloud.business.material.api.ApiConstant;
import com.bin.cloud.business.material.api.information.context.InformationMessageContext;
import com.bin.cloud.business.material.base.entity.query.InformationMessagePageQuery;
import com.bin.cloud.business.material.base.entity.query.InformationMessageQuery;
import com.bin.cloud.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Description 实时资讯
 * @Author hubin
 * @Date 2020-04-12 10:39
 * @Version 1.0
 **/
@RestController
public class InformationMessageApi {

    @Resource
    private InformationMessageContext context;

    /**
     * 根据类型获取列表数据
     * @return
     */
    @PostMapping(ApiConstant.INFORMATION_MESSAGE_LIST)
    public Result queryList(@RequestBody InformationMessagePageQuery pageQuery) {
        return context.page(pageQuery);
    }

    /**
     * 根据ID获取详细信息
     * @param params
     * @return
     */
    @PostMapping(ApiConstant.INFORMATION_MESSAGE_INFO)
    public Result queryInfo(@RequestBody HashMap<String, Long> params) {
        if (params.isEmpty()) {
            return Result.failMessage("参数为空");
        }
        return context.queryInfo(params);
    }

    /**
     * 根据类型获取列表数据
     * @return
     */
    @PostMapping(ApiConstant.INFORMATION_MESSAGE_SAVE)
    public Result save(@RequestBody InformationMessageQuery query) {
        return context.saveInfo(query);
    }

    /**
     * 增加阅读量
     * @return
     */
    @PostMapping(ApiConstant.INFORMATION_MESSAGE_ADD_READER)
    public Result addCount(@RequestBody HashMap<String, Long> params) {
        return context.addReader(params);
    }
}
