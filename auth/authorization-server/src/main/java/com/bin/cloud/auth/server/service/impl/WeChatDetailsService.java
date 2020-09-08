package com.bin.cloud.auth.server.service.impl;

import com.bin.cloud.auth.server.entity.po.Roles;
import com.bin.cloud.auth.server.entity.po.Users;
import com.bin.cloud.auth.server.entity.vo.UserInfoVo;
import com.bin.cloud.auth.server.provider.UserCoreProvider;
import com.bin.cloud.common.core.utils.DataConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-10-21 11:09
 * @Version 1.0
 **/
@Slf4j
@Service("weChatUserDetailsService")
public class WeChatDetailsService implements UserDetailsService {
    @Resource
    private UserCoreProvider userCoreProvider;

    @Override
    public UserDetails loadUserByUsername(String openid) {
        log.info("用户传参数：{}", openid);
        String openidBase64 = Base64Utils.encodeToString(openid.getBytes());
        UserInfoVo vo = userCoreProvider.getUserByOpenid(openidBase64).getData();
        Users users = DataConvert.mergeNotNullReflect(vo, Users.class);
        if (Objects.nonNull(users)) {
            log.info("loadByUsername:{}", users.toString());
            users.setId(vo.getId());
            users.setPassword(new BCryptPasswordEncoder().encode(openid)).setAccountNonExpired(true)
                    .setAccountNonExpired(true)
                    .setAccountNonLocked(true)
                    .setCredentialsNonExpired(true)
                    .setEnabled(true).setAuths(obtainGrantedAuthorities(users));
            return users;
        }
        return null;
    }

    /**
     * 获得登录者所有角色的权限集合.
     *
     * @param users
     * @return
     */
    private Set<GrantedAuthority> obtainGrantedAuthorities(Users users) {
        Set<Roles> roles = userCoreProvider.queryRolesByUserId(users.getId()).getData();
        log.info("USER:{},ROLES:{}", users.getUsername(), roles);
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getCode()))
                .collect(Collectors.toSet());
    }
}
