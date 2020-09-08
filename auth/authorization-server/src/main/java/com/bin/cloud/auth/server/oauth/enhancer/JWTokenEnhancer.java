package com.bin.cloud.auth.server.oauth.enhancer;

import com.bin.cloud.auth.server.entity.po.Users;
import com.google.common.collect.Maps;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Map;

/**
 * @Description 自定义token携带返回
 * @Author hubin
 * @Date 2019-07-16 09:28
 * @Version 1.0
 **/
public class JWTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = Maps.newHashMap();
        //自定义token内容，加入用户ID
        Users users = (Users) authentication.getPrincipal();
        users.setPassword("");
        additionalInfo.put("principal", users);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
