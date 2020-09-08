package com.bin.cloud.auth.server.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-08-17 11:07
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeChatAccessGrant {

    private String openid;
    private String access_token;
    private String refresh_token;
    private String scope;
    private Long expires_in;
}
