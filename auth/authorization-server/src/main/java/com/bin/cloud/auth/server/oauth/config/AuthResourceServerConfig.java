package com.bin.cloud.auth.server.oauth.config;

import com.bin.cloud.auth.server.oauth.validate.sms.SmsCodeFilter;
import com.bin.cloud.auth.server.oauth.validate.wechat.WeChatOauthFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-07-15 23:56
 * @Version 1.0
 **/
@Configuration
@EnableResourceServer
public class AuthResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Resource
    private SmsCodeFilter smsCodeFilter;


    @Resource
    private WeChatOauthFilter weChatOauthFilter;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加短信验证码校验过滤器
                .addFilterBefore(weChatOauthFilter,UsernamePasswordAuthenticationFilter.class) // 添加校验过滤器
                .formLogin()
                .loginProcessingUrl("/login") //处理表单登陆
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/actuator/**","/sms/code","/online/principal").permitAll()
                .anyRequest() // 所有请求
                .authenticated(); // 都需要认证;

    }
}
