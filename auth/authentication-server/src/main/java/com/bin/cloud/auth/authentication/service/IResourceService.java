package com.bin.cloud.auth.authentication.service;

import com.bin.cloud.auth.authentication.entity.po.Resources;
import com.bin.cloud.common.core.entity.dto.PageParam;
import com.bin.cloud.common.core.entity.vo.PageVo;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

@Service
public interface IResourceService {
    /**
     * 返回所有的资源定义内容，resources表中
     *
     * @return
     */
    Set<Resources> findAll();

    /**
     * 根据角色code查询到角色把对应的资源定义
     *
     * @param roleCodes
     * @return
     */
    Set<Resources> queryByRoleCodes(String[] roleCodes);

    boolean save(Resources resources);

    PageVo queryResourcePage(PageParam pageParam);

    ConfigAttribute findConfigAttributesByUrl(HttpServletRequest authRequest);

    Map<RequestMatcher, ConfigAttribute> reloadResource();

    boolean updateById(Resources entity);

    boolean removeById(Long id);

    Set<Resources> queryByUsername(String username);
}
