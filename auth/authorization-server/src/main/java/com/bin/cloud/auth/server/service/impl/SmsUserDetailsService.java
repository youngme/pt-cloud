package com.bin.cloud.auth.server.service.impl;

import com.bin.cloud.auth.server.entity.po.Roles;
import com.bin.cloud.auth.server.entity.po.Users;
import com.bin.cloud.auth.server.provider.UserCoreProvider;
import com.bin.cloud.auth.server.service.IMobileCodeManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-10-21 11:07
 * @Version 1.0
 **/
@Slf4j
@Service("smsUserDetailsService")
public class SmsUserDetailsService implements UserDetailsService {
    @Resource
    private UserCoreProvider userCoreProvider;

    @Resource
    private IMobileCodeManagerService mobileCodeManagerService;

    @Value("${sms.mobile.key}")
    private  String mobileKey; // 请求中，参数为mobile

    @Override
    public UserDetails loadUserByUsername(String mobile) {
        log.info("用户传参数：{}", mobile);
        Users users = userCoreProvider.getUserByMobile(mobile).getData();
        String code = mobileCodeManagerService.getCode(mobile);
        if (Objects.nonNull(users)) {
            log.info("loadByUsername:{}", users.toString());
            users.setPassword(new BCryptPasswordEncoder().encode(code)).setAccountNonExpired(true)
                    .setAccountNonExpired(true)
                    .setAccountNonLocked(true)
                    .setCredentialsNonExpired(true)
                    .setEnabled(true).setAuths(obtainGrantedAuthorities(users));
            mobileCodeManagerService.removeCode(mobileKey);
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
