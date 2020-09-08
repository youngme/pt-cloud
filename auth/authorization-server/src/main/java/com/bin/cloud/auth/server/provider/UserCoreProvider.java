package com.bin.cloud.auth.server.provider;

import com.bin.cloud.auth.server.entity.dto.UserInfoDTO;
import com.bin.cloud.auth.server.entity.po.Roles;
import com.bin.cloud.auth.server.entity.po.Users;
import com.bin.cloud.auth.server.entity.vo.UserInfoVo;
import com.bin.cloud.common.core.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <br>
 *
 * @author hubin
 * @title: 用户远程调用
 * @description:
 * @date: 2019/7/5 16:53
 */
@FeignClient(name = "system-core", fallback = UserCoreFallback.class)
public interface UserCoreProvider {

    @GetMapping(value = "/user")
    Result<Users> getUserByUsername(@RequestParam("username") String username);

    @GetMapping(value = "/mobile")
    Result<Users> getUserByMobile(@RequestParam("mobile") String mobile);


    @GetMapping(value = "/openid")
    Result<UserInfoVo> getUserByOpenid(@RequestParam("openid") String openid);

    @PostMapping(value = "/user/saveOrUpdate")
    Result<UserInfoVo> saveOrUpdateUserInfo(@RequestBody UserInfoDTO user);

    @GetMapping(value = "/role/user/{userId}")
    Result<Set<Roles>> queryRolesByUserId(@PathVariable("userId") long userId);

    @PostMapping(value = "/role/save/byUser")
    Result saveRoleAdminByUserId(@RequestBody() Map<String,Long> params);
}


class UserCoreFallback implements UserCoreProvider {

    @Override
    public Result<Users> getUserByUsername(String username) {
        return Result.success(new Users());
    }

    @Override
    public Result<Set<Roles>> queryRolesByUserId(long userId) {
        return Result.success(new HashSet<Roles>());
    }

    @Override
    public Result<Users> getUserByMobile(String mobile) {
        return Result.success(new Users());
    }

    @Override
    public Result<UserInfoVo> getUserByOpenid(String openid) {
        return Result.success(new Users());
    }
    @Override
    public Result saveOrUpdateUserInfo(UserInfoDTO user){
        return Result.failMessage("操作失败");
    }

    @Override
    public Result saveRoleAdminByUserId(Map<String,Long> params) {
        return Result.failMessage("权限操作失败");
    }
}
