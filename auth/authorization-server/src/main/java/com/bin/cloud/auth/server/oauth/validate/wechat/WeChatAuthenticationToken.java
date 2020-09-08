package com.bin.cloud.auth.server.oauth.validate.wechat;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Description 微信认证Token
 * @Author hubin
 * @Date 2019-10-21 11:03
 * @Version 1.0
 **/
public class WeChatAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public WeChatAuthenticationToken(Authentication authenticationToken) {
        super(authenticationToken.getPrincipal(), authenticationToken.getCredentials());
    }
}
