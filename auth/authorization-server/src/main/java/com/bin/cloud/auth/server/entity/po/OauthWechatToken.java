package com.bin.cloud.auth.server.entity.po;

import com.bin.cloud.common.core.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 用户微信授权token
 * @Author hubin
 * @Date 2019-10-18 13:39
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OauthWechatToken{

    private String openid;              // 用户唯一标识
    private String accessToken;         // 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
    private Long expiresIn;             // access_token接口调用凭证超时时间，单位（秒）
    private String refreshToken;        // 用户刷新access_token
    private String scope;               // 用户授权的作用域，使用逗号（,）分隔
}
