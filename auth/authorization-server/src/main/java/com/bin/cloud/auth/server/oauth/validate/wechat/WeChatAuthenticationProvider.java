package com.bin.cloud.auth.server.oauth.validate.wechat;

import com.bin.cloud.auth.server.oauth.validate.sms.SmsCodeAuthenticationToken;
import com.bin.cloud.auth.server.service.impl.WeChatDetailsService;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-10-21 11:13
 * @Version 1.0
 **/
@Data
public class WeChatAuthenticationProvider extends DaoAuthenticationProvider {

    public WeChatAuthenticationProvider(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return WeChatAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
