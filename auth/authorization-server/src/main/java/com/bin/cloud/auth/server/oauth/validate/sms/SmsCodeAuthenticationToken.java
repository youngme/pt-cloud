package com.bin.cloud.auth.server.oauth.validate.sms;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/7/10 10:58
 */
public class SmsCodeAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public SmsCodeAuthenticationToken(Authentication authenticationToken) {
        super(authenticationToken.getPrincipal(), authenticationToken.getCredentials());
    }
}
