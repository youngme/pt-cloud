package com.bin.cloud.business.material.api.jccMaterial;

import com.bin.cloud.business.material.api.ApiConstant;
import com.bin.cloud.business.material.api.jccMaterial.context.JccMaterialContext;
import com.bin.cloud.business.material.base.entity.dto.JccMaterialInfoDTO;
import com.bin.cloud.business.material.base.entity.query.JccMaterialInfoQuery;
import com.bin.cloud.business.material.base.entity.query.JccMaterialPageQuery;
import com.bin.cloud.common.core.entity.vo.Result;
import com.bin.cloud.common.core.exception.ErrorType;
import com.bin.cloud.common.core.exception.SystemErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description 建材信息
 * @Author hubin
 * @Date 2019-10-10 14:07
 * @Version 1.0
 **/
@RestController
public class JccMaterialApi {

    @Resource
    private JccMaterialContext context;

    /**
     * 根据类型获取列表数据
     * @return
     */
    @PostMapping(ApiConstant.JCC_MATERIAL_LIST_BY_TYPE)
    public Result getListByType() {
        return context.queryListByType();
    }

    /**
     * 保存
     * @param query
     * @return
     */
    @PostMapping(ApiConstant.JCC_MATERIAL_SAVE)
    public Result save(@RequestBody JccMaterialInfoQuery query) {
        return context.save(query);
    }

    /**
     * 更新
     * @param dto
     * @return
     */
    @PostMapping(ApiConstant.JCC_MATERIAL_UPDATE)
    public Result update(@RequestBody JccMaterialInfoDTO dto){
        return context.update(dto);
    }

    /**
     * 根据关系ID获取信息
     * @param params
     * @return
     */
    @PostMapping(ApiConstant.JCC_MATERIAL_GET)
    public Result getInfoById(@RequestBody Map<String,Long> params) {
        return context.queryInfoById(params);
    }


    /**
     * 获取分页
     * @param pageQuery
     * @return
     */
    @PostMapping(ApiConstant.JCC_MATERIAL_PAGE)
    public Result queryPage(@RequestBody JccMaterialPageQuery pageQuery) {
        return context.queryPage(pageQuery);
    }

    /**
     * 根据用户ID获取相关的发布信息
     * @param params
     * @return
     */
    @PostMapping(ApiConstant.JCC_MATERIAL_LIST_BY_USERID)
    public Result queryListByUserId(@RequestBody Map<String,String> params) {
        if (params.isEmpty()) {
            return Result.failMessage("参数为空");
        }
        return context.queryListByUserId(Long.parseLong(params.get("userId")));
    }

    @PostMapping(ApiConstant.QUERY_JCC_MATERIAL_PUSH_TOTAL_BY_USER_ID)
    public Result queryPushTotalByUser(@RequestBody Map<String, Long> params) {
        return context.queryPushTotalByUser(params);
    }

    @PostMapping(ApiConstant.JCC_MATERIAL_REMOVE_BY_ID)
    public Result remove(@RequestBody Map<String, Long> params) {
        return context.remove(params);
    }

}
