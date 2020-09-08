package com.bin.cloud.system.core.api.role;

import com.bin.cloud.common.core.entity.vo.Result;
import com.bin.cloud.system.core.api.ApiConstant;
import com.bin.cloud.system.core.api.role.context.RoleContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description 用户角色信息接口
 * @Author hubin
 * @Date 2019-10-21 17:46
 * @Version 1.0
 **/
@RestController
public class RoleApi {

    @Resource
    private RoleContext roleContext;

    @GetMapping(ApiConstant.OAUTH2_ROLE_BY_USERID)
    public Result getRoleByUid(@PathVariable("userId") Long userId) {
        return roleContext.queryUserRolesByUserId(userId);
    }

    @GetMapping(ApiConstant.OAUTH2_ROLE_SAVE_BY_USER)
    public Result saveRoleByUid(@RequestBody Map<String,Long> params) {
        if (params.isEmpty()) {
            return Result.failMessage("参数为空");
        }
        return roleContext.saveRoleByUid(params.get("userId"));
    }

}
