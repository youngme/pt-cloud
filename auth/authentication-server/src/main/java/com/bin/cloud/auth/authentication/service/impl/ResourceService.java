package com.bin.cloud.auth.authentication.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.cloud.auth.authentication.dao.ResourceMapper;
import com.bin.cloud.auth.authentication.entity.po.Resources;
import com.bin.cloud.auth.authentication.service.IAuthenticationService;
import com.bin.cloud.auth.authentication.service.IResourceService;
import com.bin.cloud.auth.authentication.service.NewMvcRequestMatcher;
import com.bin.cloud.common.core.entity.dto.PageParam;
import com.bin.cloud.common.core.entity.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ResourceService extends ServiceImpl<ResourceMapper, Resources> implements IResourceService {

    @Resource
    IAuthenticationService authenticationService;

    @Resource
    private HandlerMappingIntrospector mvcHandlerMappingIntrospector;

    /**
     * 系统中所有权限集合
     */
    Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes;

    @Override
    public Set<Resources> findAll() {
        return baseMapper.findAll();
    }

    @Override
    public Set<Resources> queryByRoleCodes(String[] roleCodes) {
        if (ArrayUtils.isNotEmpty(roleCodes)) {
            return baseMapper.queryByRoleCodes(roleCodes);
        }
        return Collections.emptySet();
    }

    @Override
    public boolean save(Resources resources) {
        if (super.save(resources)) {
            resourceConfigAttributes.put(
                    newMvcRequestMatcher(resources.getUrl(), resources.getMethod()),
                    new SecurityConfig(resources.getCode()));
            log.info("resourceConfigAttributes size:{}", this.resourceConfigAttributes.size());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 根据url和method查询到对应的权限信息
     *
     * @param authRequest
     * @return
     */
    public ConfigAttribute findConfigAttributesByUrl(HttpServletRequest authRequest) {
        return this.resourceConfigAttributes.keySet().stream()
                .filter(requestMatcher -> requestMatcher.matches(authRequest))
                .map(requestMatcher -> this.resourceConfigAttributes.get(requestMatcher))
                .peek(urlConfigAttribute -> log.debug("url在资源池中配置：{}", urlConfigAttribute.getAttribute()))
                .findFirst()
                .orElse(new SecurityConfig(AuthenticationService.NONEXISTENT_URL));
    }


    @Override
    public PageVo queryResourcePage(PageParam pageParam) {
        List<Resources> pageList = baseMapper.queryResourcePage(pageParam.build());
        Long total = baseMapper.queryPageTotal();
        return PageVo.build().rows(pageList).total(total);
    }

    @Override
    public Map<RequestMatcher, ConfigAttribute> reloadResource() {
        Set<Resources> resources = this.findAll();
        this.resourceConfigAttributes = resources.stream()
                .collect(Collectors.toMap(
                        resource -> {
                            MvcRequestMatcher mvcRequestMatcher = new MvcRequestMatcher(mvcHandlerMappingIntrospector, resource.getUrl());
                            mvcRequestMatcher.setMethod(HttpMethod.resolve(resource.getMethod()));
                            return mvcRequestMatcher;
                        },
                        resource -> new SecurityConfig(resource.getCode())
                        )
                );
        log.debug("加载数据库中的资源信息：resourceConfigAttributes:{}", this.resourceConfigAttributes.size());
        return this.resourceConfigAttributes;
    }

    @Override
    public boolean updateById(Resources entity) {
        if (super.updateById(entity)) {
            this.resourceConfigAttributes = this.reloadResource();
            log.info("更新resourceConfigAttributes size:{}", this.resourceConfigAttributes.size());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public boolean removeById(Long id) {
        if (super.removeById(id)) {
            this.resourceConfigAttributes = this.reloadResource();
            log.info("更新resourceConfigAttributes size:{}", this.resourceConfigAttributes.size());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 创建RequestMatcher
     *
     * @param url
     * @param method
     * @return
     */
    private MvcRequestMatcher newMvcRequestMatcher(String url, String method) {
        return new NewMvcRequestMatcher(mvcHandlerMappingIntrospector, url, method);
    }

    @Override
    public Set<Resources> queryByUsername(String username) {
        if (StringUtils.isNotBlank(username)) {
            return baseMapper.queryByUsername(username);
        }
        return Collections.emptySet();
    }
}
