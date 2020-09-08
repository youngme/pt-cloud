package com.bin.cloud.auth.authentication.service;

import lombok.Getter;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-08-10 10:11
 * @Version 1.0
 **/
@Getter
public class NewMvcRequestMatcher extends MvcRequestMatcher {

    private String url;
    private String method;

    public NewMvcRequestMatcher(HandlerMappingIntrospector introspector, String url, String method) {
        super(introspector,url);
        this.setMethod(HttpMethod.resolve(method));
        this.url = url;
        this.method = method;
    }
}
