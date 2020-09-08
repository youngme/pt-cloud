package com.bin.cloud.auth.server.service.impl;

import com.bin.cloud.auth.server.entity.vo.WeChatAccessGrant;
import com.bin.cloud.auth.server.entity.vo.WeChatUserInfo;
import com.bin.cloud.auth.server.service.IWeChatManagerService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Description 微信登陆
 * @Author hubin
 * @Date 2019-08-17 11:06
 * @Version 1.0
 **/
@Service
public class WeChatManagerService extends AbstractOAuth2ApiBinding implements IWeChatManagerService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weChat.getTokenUrl}")
    private String getTokenUrl;

    @Value("${weChat.refreshTokenUrl}")
    private String refreshTokenUrl;

    @Value("${weChat.getUserInfoUrl}")
    private String getUserInfoUrl;

    @Value("${weChat.checkTokenUrl}")
    private String checkTokenUrl;

    @Override
    public WeChatUserInfo getUserInfo(String code) {
        WeChatAccessGrant token = this.getAccessToken(code);
        if (Objects.nonNull(token)) {
//            redisTemplate.opsForValue().set(String.valueOf(token.getAccess_token().hashCode()), token,2, TimeUnit.HOURS);
            String uri = getUserInfoUrl.replace("ACCESS_TOKEN", token.getAccess_token())
                    .replace("OPENID", token.getOpenid());
            ResponseEntity<WeChatUserInfo> userEntity = restTemplate.getForEntity(uri, WeChatUserInfo.class);
            if (userEntity.getStatusCode().equals(HttpStatus.OK)) {
                WeChatUserInfo userInfo = userEntity.getBody();

//                redisTemplate.opsForValue().set(String.valueOf(userInfo.getOpenid().hashCode()), userInfo);
                return userInfo;
            }
        }
        return null;
    }

    public WeChatAccessGrant getAccessToken(String code) {
        String uri = getTokenUrl.replace("CODE", code);
        ResponseEntity<WeChatAccessGrant> responseEntity = restTemplate.getForEntity(uri, WeChatAccessGrant.class);
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            WeChatAccessGrant weChatAccessGrant = responseEntity.getBody();
            return weChatAccessGrant;
        }
        return null;
    }

}
