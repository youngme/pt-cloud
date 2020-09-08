package com.bin.cloud.auth.client.provider;

import com.bin.cloud.auth.client.config.FeignFormConfiguration;
import com.bin.cloud.auth.client.provider.dto.TokenDTO;
import com.bin.cloud.common.core.entity.vo.Result;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * @Description 认证服务调用
 * @Author hubin
 * @Date 2019-12-23 12:25
 * @Version 1.0
 **/

@Component
@FeignClient(name = "oauth-server",
        fallback = OauthProvider.OauthProviderFallback.class,
        configuration = FeignFormConfiguration.class)
public interface OauthProvider {

    @PostMapping(value = "/oauth/check_token",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @Headers(value = {"Authorization:Basic YWRtaW5fY2xpZW50OmFkbWluX3NlY3JldA==",
                    "Content-Type:application/x-www-form-urlencoded"})
    Object checkToken(TokenDTO dto);

    @Component
    class OauthProviderFallback implements OauthProvider {
        /**
         * 降级统一返回无权限
         *
         * @param dto
         * @return <pre>
         * Result:
         * {
         *   code:"-1"
         *   mesg:"系统异常"
         * }
         * </pre>
         */
        @Override
        public Object checkToken(TokenDTO dto) {
            return Result.fail();
        }
    }
}
