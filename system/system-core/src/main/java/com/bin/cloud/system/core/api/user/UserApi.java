package com.bin.cloud.system.core.api.user;

import com.bin.cloud.common.core.entity.vo.Result;
import com.bin.cloud.system.core.api.ApiConstant;
import com.bin.cloud.system.core.api.user.context.UserContext;
import com.bin.cloud.system.core.base.entity.dto.UserInfoDTO;
import com.bin.cloud.system.core.base.entity.vo.UserInfoVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description 用户接口API
 * @Author hubin
 * @Date 2019-10-21 17:16
 * @Version 1.0
 **/
@RestController
public class UserApi {

    @Resource
    private UserContext userContext;

    @GetMapping(ApiConstant.OAUTH2_USER_BY_USERNAME)
    public Result getUserByUsername(@RequestParam("username")String username) {
        return userContext.getByUsername(username);
    }

    @GetMapping(ApiConstant.OAUTH2_USER_BY_MOBILE)
    public Result getUserByMobile(@RequestParam("mobile")String mobile) {
        return userContext.getByMobile(mobile);
    }

    @GetMapping(ApiConstant.OAUTH2_USER_BY_OPENID)
    Result<UserInfoVo> getUserByOpenid(@RequestParam("openid") String openid){
        return userContext.getByOpenid(openid);
    }

    @PostMapping(ApiConstant.OAUTH2_USER_SAVE_OR_UPDATE)
    Result<UserInfoVo> saveOrUpdateUserInfo(@RequestBody UserInfoDTO user){
        return userContext.saveOrUpdateUserInfo(user);
    }

}
