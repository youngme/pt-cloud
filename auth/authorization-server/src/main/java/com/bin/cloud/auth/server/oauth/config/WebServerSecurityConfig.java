package com.bin.cloud.auth.server.oauth.config;

import com.bin.cloud.auth.server.oauth.validate.sms.SmsCodeAuthenticationProvider;
import com.bin.cloud.auth.server.oauth.validate.sms.SmsCodeFilter;
import com.bin.cloud.auth.server.oauth.validate.wechat.WeChatAuthenticationProvider;
import com.bin.cloud.auth.server.oauth.validate.wechat.WeChatOauthFilter;
import com.bin.cloud.auth.server.service.impl.CustomUserDetailsService;
import com.bin.cloud.auth.server.service.impl.SmsUserDetailsService;
import com.bin.cloud.auth.server.service.impl.WeChatDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-07-16 22:03
 * @Version 1.0
 **/
@Configuration
@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebServerSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("weChatUserDetailsService")
    private UserDetailsService weChatDetailsService;

    @Autowired
    @Qualifier("smsUserDetailsService")
    private UserDetailsService smsUserDetailsService;

    /**
     * 将 AuthenticationManager 注册为 bean , 方便配置 oauth server 的时候使用
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 注入自定义的userDetailsService实现，获取用户信息，设置密码加密方式
     *
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        // 设置手机验证码登陆的AuthenticationProvider
        authenticationManagerBuilder.authenticationProvider(mobileAuthenticationProvider());
        // 设置微信登陆的AuthenticationProvider
        authenticationManagerBuilder.authenticationProvider(weChatAuthenticationProvider());
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/actuator/**","/sms/code").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll();
//    }
//


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 创建手机验证码登陆的AuthenticationProvider
     *
     * @return mobileAuthenticationProvider
     */
    @Bean
    public SmsCodeAuthenticationProvider mobileAuthenticationProvider() {
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider(this.smsUserDetailsService);
        smsCodeAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return smsCodeAuthenticationProvider;
    }

    /**
     * 创建微信登陆的AuthenticationProvider
     *
     * @return mobileAuthenticationProvider
     */
    @Bean
    public WeChatAuthenticationProvider weChatAuthenticationProvider() {
        WeChatAuthenticationProvider weChatAuthenticationProvider = new WeChatAuthenticationProvider(this.weChatDetailsService);
        weChatAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return weChatAuthenticationProvider;
    }
}