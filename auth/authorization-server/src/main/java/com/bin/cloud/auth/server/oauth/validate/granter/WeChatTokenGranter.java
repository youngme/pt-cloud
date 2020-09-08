package com.bin.cloud.auth.server.oauth.validate.granter;

import com.bin.cloud.auth.server.oauth.validate.wechat.WeChatAuthenticationToken;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-10-23 10:17
 * @Version 1.0
 **/
public class WeChatTokenGranter extends ResourceOwnerPasswordTokenGranter {

    private static final String GRANT_TYPE = "weChat";

    private AuthenticationManager authenticationManager;

    public WeChatTokenGranter(AuthenticationManager authenticationManager,
                              AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
                              OAuth2RequestFactory requestFactory) {
        super(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        String openid = parameters.get("openid");

        Authentication userAuth = new UsernamePasswordAuthenticationToken(openid, openid);
        WeChatAuthenticationToken weChatAuthenticationToken = new WeChatAuthenticationToken(userAuth);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        try {
            userAuth = this.authenticationManager.authenticate(weChatAuthenticationToken);
        } catch (AccountStatusException | BadCredentialsException ase) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            // If the username/password are wrong the spec says we should send 400/invalid grant
            throw new InvalidGrantException(ase.getMessage());
        }

        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user openid: " + openid);
        }

        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, new WeChatAuthenticationToken(userAuth));
    }
}
