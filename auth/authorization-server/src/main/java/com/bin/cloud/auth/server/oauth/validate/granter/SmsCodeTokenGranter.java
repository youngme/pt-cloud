package com.bin.cloud.auth.server.oauth.validate.granter;

import com.bin.cloud.auth.server.entity.po.Users;
import com.bin.cloud.auth.server.oauth.validate.sms.SmsCodeAuthenticationToken;
import com.bin.cloud.auth.server.oauth.validate.wechat.WeChatAuthenticationToken;
import com.bin.cloud.auth.server.provider.UserCoreProvider;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-10-23 10:16
 * @Version 1.0
 **/
public class SmsCodeTokenGranter extends ResourceOwnerPasswordTokenGranter {

    private static final String GRANT_TYPE = "mobile";

    private AuthenticationManager authenticationManager;

    public SmsCodeTokenGranter(AuthenticationManager authenticationManager,
                               AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
                               OAuth2RequestFactory requestFactory) {
        super(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        String mobile = parameters.get("mobile");
        String code = parameters.get("code");
        // Protect from downstream leaks of password
        Authentication userAuth = new UsernamePasswordAuthenticationToken(mobile, code);
        SmsCodeAuthenticationToken smsCodeAuthenticationToken = new SmsCodeAuthenticationToken(userAuth);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        try {
            userAuth = this.authenticationManager.authenticate(smsCodeAuthenticationToken);
        } catch (AccountStatusException |BadCredentialsException exception) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            throw new InvalidGrantException(exception.getMessage());
        }
        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + mobile);
        }
        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request,  new SmsCodeAuthenticationToken(userAuth));
    }
}