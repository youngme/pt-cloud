package com.bin.cloud.system.web.rest;

import com.bin.cloud.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/7/4 14:57
 */
@RestController
public class UserController {

    @PostMapping("/users")
    public Result getUserInfo() {
        return Result.success("用户保存成功");
    }
}
