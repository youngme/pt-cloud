package com.bin.cloud.auth.server.service.impl;

import com.bin.cloud.auth.server.entity.po.Roles;
import com.bin.cloud.auth.server.entity.po.Users;
import com.bin.cloud.auth.server.provider.UserCoreProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCoreProvider userCoreProvider;

    @Override
    public UserDetails loadUserByUsername(String param) {
        log.info("用户传参数：{}", param);
        Users users = userCoreProvider.getUserByUsername(param).getData();
        if (Objects.nonNull(users)) {
            log.info("loadByUsername:{}", users.toString());
            users.setAccountNonExpired(true)
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
