package com.bin.cloud.system.core.web.role;

import com.bin.cloud.common.core.entity.dto.PageParam;
import com.bin.cloud.common.core.entity.vo.Result;
import com.bin.cloud.system.core.base.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <br>
 *
 * @author hubin
 * @title: 角色
 * @description:
 * @date: 2019/7/6 11:05
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    RoleService roleService;
    /**
     * 角色分页
     * @param pageParam
     * @return
     */
    @GetMapping(value = "/pageList")
    public Result pageJson(PageParam pageParam){
        return Result.success(roleService.queryRolePage(pageParam));
    }

    /**
     * 角色列表
     * @return
     */
    @GetMapping("/list")
    public Result getRoleList(){
        return Result.success(roleService.queryRoleList());
    }

}
