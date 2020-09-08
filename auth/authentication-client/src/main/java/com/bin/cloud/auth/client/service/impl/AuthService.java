package com.bin.cloud.auth.client.service.impl;

import com.bin.cloud.auth.client.provider.AuthProvider;
import com.bin.cloud.auth.client.provider.OauthProvider;
import com.bin.cloud.auth.client.provider.dto.TokenDTO;
import com.bin.cloud.auth.client.service.IAuthService;
import com.bin.cloud.common.core.entity.vo.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.InvalidSignatureException;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Stream;

@Service
@Slf4j
public class AuthService implements IAuthService {

    @Resource
    private AuthProvider authProvider;

//    @Resource
//    private OauthProvider oauthProvider;

    @Resource
    private RestTemplate restTemplate;

    /**
     * Authorization认证开头是"bearer "
     */
    private static final int BEARER_BEGIN_INDEX = 7;

    /**
     * jwt token 密钥，主要用于token解析，签名验证
     */
    @Value("${spring.security.oauth2.jwt.signingKey}")
    private String signingKey;

    @Value("${oauth.token.oauthorizationKey}")
    private String oauthorizationKey;

    /**
     * 不需要网关签权的url配置(/oauth,/open)
     * 默认/oauth开头是不需要的
     */
    @Value("${gate.ignore.authentication.startWith}")
    private String ignoreUrls = "/oauth";
    /**
     * jwt验签
     */
    private MacSigner verifier;

    @Override
    public Result authenticate(String authentication, String url, String method) {
        return authProvider.auth(authentication, url, method);
    }

    @Override
    public boolean ignoreAuthentication(String url) {
        return Stream.of(this.ignoreUrls.split(",")).anyMatch(ignoreUrl -> url.startsWith(StringUtils.trim(ignoreUrl)));
    }

    @Override
    public boolean hasPermission(Result authResult) {
        return authResult.isSuccess() && (boolean) authResult.getData();
    }

    @Override
    public boolean hasPermission(String authentication, String url, String method) {
        //token是否有效
//        if (invalidJwtAccessToken(authentication)) {
//            return Boolean.FALSE;
//        }
        //从认证服务获取是否有权限
        return hasPermission(authenticate(authentication, url, method));
    }

    @Override
    public boolean invalidJwtAccessToken(String authentication) {
        verifier = Optional.ofNullable(verifier).orElse(new MacSigner(signingKey));
        //是否无效true表示无效
        boolean invalid = Boolean.FALSE;

        try {
            String token = getToken(authentication);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", oauthorizationKey);
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("token", token);
//            ObjectMapper mapper = new ObjectMapper();
//            String value = mapper.writeValueAsString(params);
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
            try {
                ResponseEntity<Map> responseEntity = restTemplate.postForEntity("http://oauth-server/oauth/check_token", requestEntity, Map.class);
                if ("020001".equals(Objects.requireNonNull(responseEntity.getBody()).get("code"))) {
                    invalid =  Boolean.TRUE;
                }
            } catch (Exception e) {
                e.printStackTrace();
                invalid =  Boolean.TRUE;
            }
        } catch (InvalidSignatureException | IllegalArgumentException ex) {
            log.warn("core token has expired or signature error ");
        }
        return invalid;
    }

    @Override
    public String getToken(String authentication) {
        return StringUtils.substring(authentication, BEARER_BEGIN_INDEX);
    }

    @Override
    public Jwt getJwt(String authentication) {
        return JwtHelper.decode(StringUtils.substring(authentication, BEARER_BEGIN_INDEX));
    }

    @Data
    class checkTokenEntity{
        private String code;
        private String mesg;
        private Date timestamp;
    }
}
