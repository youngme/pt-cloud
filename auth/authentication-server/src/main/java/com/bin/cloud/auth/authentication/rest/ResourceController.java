package com.bin.cloud.auth.authentication.rest;

import com.bin.cloud.auth.authentication.entity.po.Resources;
import com.bin.cloud.auth.authentication.service.IResourceService;
import com.bin.cloud.common.core.entity.dto.PageParam;
import com.bin.cloud.common.core.entity.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-08-06 23:01
 * @Version 1.0
 **/
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    IResourceService resourceService;

    /**
     * 资源保存
     * @param resources
     * @return
     */
    @PostMapping(value = "/save")
    public Result save(@RequestBody Resources resources) {
        if (resourceService.save(resources)) {
            return Result.success();
        }
        return Result.fail();
    }

    @PostMapping(value = "/update")
    public Result update(@RequestBody Resources resources) {
        if (resourceService.updateById(resources)) {
            return Result.success();
        }
        return Result.fail();
    }

    @PostMapping(value = "/remove")
    public Result remove(@RequestBody Long id) {
        if (resourceService.removeById(id)) {
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 资源分页
     * @param pageParam
     * @return
     */
    @GetMapping(value = "/pageList")
    public Result pageJson(PageParam pageParam){
        return Result.success(resourceService.queryResourcePage(pageParam));
    }
}
