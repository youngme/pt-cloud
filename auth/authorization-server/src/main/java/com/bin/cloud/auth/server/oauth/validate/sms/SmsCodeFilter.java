package com.bin.cloud.auth.server.oauth.validate.sms;

import com.alibaba.fastjson.JSON;
import com.bin.cloud.auth.server.exception.AuthErrorType;
import com.bin.cloud.auth.server.exception.ValidateCodeException;
import com.bin.cloud.auth.server.service.IMobileCodeManagerService;
import com.bin.cloud.auth.server.utils.SmsCodeUtils;
import com.bin.cloud.common.core.entity.vo.Result;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <br>
 *
 * @author hubin
 * @title: 验证码过滤器
 * @description:
 * @date: 2019/7/10 14:16
 */
@Data
@Slf4j
@Component
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Value("${sms.mobile.request.api}")
    private String requestUrl; // 需要拦截的请求地址
    @Value("${sms.mobile.key}")
    private  String mobileKey; // 请求中，参数为mobile


    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler; // 验证码验证失败处理器
    private Set<String> urls = new HashSet<>();
    private IMobileCodeManagerService mobileCodeManagerService; // 验证码管理

    private AntPathMatcher antPathMatcher = new AntPathMatcher(); // 验证请求url与配置的url是否匹配的工具类

    public SmsCodeFilter(IMobileCodeManagerService mobileCodeManagerService) {
        this.mobileCodeManagerService = mobileCodeManagerService;
    }

    /**
     * 初始化要拦截的url配置信息
     *
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configs = StringUtils.splitByWholeSeparatorPreserveAllTokens(
                new SmsCodeUtils().getUrl(), ","
        );
        Arrays.asList(configs).forEach(urls::add);
        urls.add(requestUrl);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false,isException = false;
        if (urls.stream().filter(url -> antPathMatcher.match(url, httpServletRequest.getRequestURI())).count() > 0) {
            action = true;
        }
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        String grantType = httpServletRequest.getParameter("grant_type");
        if (action && grantType.equals(mobileKey)) {
            try {
                //获取参数中的mobile
//                HashMap<String, String> params = HttpUtils.obtainMobile(httpServletRequest);
                String mobile = httpServletRequest.getParameter(mobileKey);
                log.info("获取到的手机号::{}", mobile);
                if (StringUtils.isNotBlank(mobile)) {
                    String code = mobileCodeManagerService.getCode(mobile);
                    log.info("获取到的redis中的code:{}", code);
                    if (StringUtils.isBlank(code)) {
                        isException = true;
                        Result result = Result.fail(AuthErrorType.MOBILE_CODE_EXPIRED);
                        httpServletResponse.getWriter().write(JSON.toJSONString(result));
                        throw new ValidateCodeException("验证码已经过期,手机号为:" + mobile);
                    }
                    String requestCode = httpServletRequest.getParameter("code");
                    if (!requestCode.equals(code)) {
                        isException = true;
                        Result result = Result.fail(AuthErrorType.MOBILE_CODE_NOT_MATCH);
                        httpServletResponse.getWriter().write(JSON.toJSONString(result));
                        throw new ValidateCodeException("验证码不匹配");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!isException) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }

    }
}
