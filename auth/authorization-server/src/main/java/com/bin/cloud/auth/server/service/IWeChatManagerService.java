package com.bin.cloud.auth.server.service;

import com.bin.cloud.auth.server.entity.vo.WeChatAccessGrant;
import com.bin.cloud.auth.server.entity.vo.WeChatUserInfo;

/**
 * @Description 微信登陆服务
 * @Author hubin
 * @Date 2019-08-17 10:56
 * @Version 1.0
 **/
public interface IWeChatManagerService {

    WeChatUserInfo getUserInfo(String code);

    WeChatAccessGrant getAccessToken(String code);

}
