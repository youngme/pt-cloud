package com.bin.cloud.auth.server.oauth.validate.sms;

import com.bin.cloud.auth.server.service.impl.CustomUserDetailsService;
import com.bin.cloud.auth.server.service.impl.SmsUserDetailsService;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/7/10 09:46
 */
public class SmsCodeAuthenticationProvider extends DaoAuthenticationProvider {

    public SmsCodeAuthenticationProvider(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
