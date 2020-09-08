package com.bin.cloud.auth.server.oauth.validate.wechat;

import com.alibaba.fastjson.JSON;
import com.bin.cloud.auth.server.entity.dto.UserInfoDTO;
import com.bin.cloud.auth.server.entity.vo.UserInfoVo;
import com.bin.cloud.auth.server.entity.vo.WeChatUserInfo;
import com.bin.cloud.auth.server.exception.AuthErrorType;
import com.bin.cloud.auth.server.exception.ValidateCodeException;
import com.bin.cloud.auth.server.provider.UserCoreProvider;
import com.bin.cloud.auth.server.service.IWeChatManagerService;
import com.bin.cloud.auth.server.utils.CommonConstants;
import com.bin.cloud.common.core.entity.vo.Result;
import com.bin.cloud.common.core.utils.DataConvert;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-10-21 09:39
 * @Version 1.0
 **/
@Data
@Slf4j
@Component
public class WeChatOauthFilter extends OncePerRequestFilter implements InitializingBean {

    @Value("${weChat.key.request.api}")
    private String requestUrl; // 需要拦截的请求地址
    @Value("${weChat.key.weChatKey}")
    private String weChatKey; // 请求中，参数为code
    @Value("${weChat.key.grantType}")
    private String grantType; // 请求中  参数为weChat

    private UserCoreProvider provider;      // 用户信息操作

    private IWeChatManagerService weChatManagerService; // 微信服务

    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler; // 验证码验证失败处理器

    private Set<String> urls = new HashSet<>();

    private AntPathMatcher antPathMatcher = new AntPathMatcher(); // 验证请求url与配置的url是否匹配的工具类

    public WeChatOauthFilter(IWeChatManagerService weChatManagerService,UserCoreProvider provider) {
        this.weChatManagerService = weChatManagerService;
        this.provider = provider;
    }

    /**
     * 初始化要拦截的url配置信息
     *
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
//        String[] configs = StringUtils.splitByWholeSeparatorPreserveAllTokens(
//                new SmsCodeUtils().getUrl(), ","
//        );
//        Arrays.asList(configs).forEach(urls::add);
        urls.add(requestUrl);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false, isException = false;
        String openid;
        HttpServletRequest finalRequest = request;
        if (urls.stream().filter(url -> antPathMatcher.match(url, finalRequest.getRequestURI())).count() > 0) {
            action = true;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
//        ParameterRequestWrapper parameterRequestWrapper = new ParameterRequestWrapper(request);
        String requestGrantType = request.getParameter("grant_type");
        if (action && grantType.equals(requestGrantType)) {
            try {
                String code = request.getParameter(weChatKey);
                log.info("获取到的code::{}", code);
                if (StringUtils.isNotBlank(code)) {
                    openid = this.checkUserExist(code);
                    log.info("获取到的redis中的code:{}", code);
                    if (StringUtils.isBlank(openid)) {
                        Result result = Result.fail(AuthErrorType.WECHAT_OPENID_NO_FOUND);
                        response.getWriter().write(JSON.toJSONString(result));
                        throw new ValidateCodeException("微信登陆失败");
                    }
                }else{
                    Result result = Result.fail(AuthErrorType.INVALID_PARAMS);
                    response.getWriter().write(JSON.toJSONString(result));
                    throw new ValidateCodeException("无效参数");
                }
                Map<String, String[]> params = new HashMap<>(request.getParameterMap());
                params.put("openid", new String[] { openid});
                request = new ParameterRequestWrapper(request, params);
            } catch (Exception e) {
                e.printStackTrace();
                isException = true;
                Result result = Result.fail(AuthErrorType.WECHAT_OPENID_NO_FOUND);
                response.getWriter().write(JSON.toJSONString(result));
            }
        }
        if (!isException) {
            filterChain.doFilter(request, response);
        }
    }


    private String checkUserExist(String code) {
        WeChatUserInfo weChatUserInfo = weChatManagerService.getUserInfo(code);
        if (Objects.nonNull(weChatUserInfo)) {
            String openidBase64 = Base64Utils.encodeToString(weChatUserInfo.getOpenid().getBytes());
            Result result = provider.getUserByOpenid(openidBase64);
            if (result.getCode().equals(Result.SUCCESSFUL_CODE)) {
                UserInfoDTO userInfoDTO = new UserInfoDTO();
                if (Objects.nonNull(result.getData())) {
                    UserInfoVo user = (UserInfoVo) result.getData();
                    if (StringUtils.isBlank(user.getOpenid())) {
                        user.setOpenid(openidBase64);
                    }
                    DataConvert.mergeNotNullReflect(user, userInfoDTO);

                }else{
                    DataConvert.mergeNotNullReflect(weChatUserInfo, userInfoDTO);
                    userInfoDTO.setOpenid(openidBase64);
                }
                if (StringUtils.isBlank(userInfoDTO.getUsername())) {
                    userInfoDTO.setUsername(CommonConstants.getRandomName());
                }
                provider.saveOrUpdateUserInfo(userInfoDTO);
            }
        }
        return weChatUserInfo.getOpenid();
    }
}
