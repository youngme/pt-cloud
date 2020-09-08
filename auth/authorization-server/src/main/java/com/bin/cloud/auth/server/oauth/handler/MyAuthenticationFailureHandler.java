package com.bin.cloud.auth.server.oauth.handler;

import com.alibaba.fastjson.JSON;
import com.bin.cloud.auth.server.exception.AuthErrorType;
import com.bin.cloud.common.core.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <br>
 *
 * @author hubin
 * @title: 登陆失败处理器
 * @description:
 * @date: 2019/7/11 16:22
 */
@Slf4j
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
        log.info("登陆失败");

        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        Result result = Result.fail(AuthErrorType.LOGIN_FAILED);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
