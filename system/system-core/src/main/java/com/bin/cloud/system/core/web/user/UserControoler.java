package com.bin.cloud.system.core.web.user;

import com.bin.cloud.common.core.entity.dto.PageParam;
import com.bin.cloud.common.core.entity.vo.Result;
import com.bin.cloud.system.core.base.entity.po.Users;
import com.bin.cloud.system.core.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 用户
 * @Author hubin
 * @Date 2019-10-21 17:20
 * @Version 1.0
 **/
@RestController
public class UserControoler {

    @Autowired
    UserService userService;


    /**
     * 用户分页
     * @param pageParam
     * @return
     */
    @GetMapping(value = "/users/pageList")
    public Result pageJson(PageParam pageParam){
        return Result.success(userService.queryUserPage(pageParam));
    }

    /**
     * 用户注册
     * @param users
     * @return
     */
    @PostMapping("/users/registry")
    public Result registryUser(@RequestBody Users users) {
        return Result.success(userService.saveUser(users));
    }

}
