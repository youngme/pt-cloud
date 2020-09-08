package com.bin.cloud.auth.authentication.config;

import com.bin.cloud.auth.authentication.entity.po.Resources;
import com.bin.cloud.auth.authentication.service.IResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by zhoutaoo on 2018/5/25.
 */
@Component
@Slf4j
class LoadResourceDefine {

    @Resource
    private IResourceService resourceService;

    @Bean
    public Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes() {

        return resourceService.reloadResource();
    }
}
