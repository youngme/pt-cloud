package com.bin.cloud.auth.server.config;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Feign配置
 * @Author hubin
 * @Date 2019-10-28 15:39
 * @Version 1.0
 **/
@Configuration
public class AuthFeignConfiguration {
    public static int connectTimeOutMillis = 12000;//超时时间
    public static int readTimeOutMillis = 12000;
    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
    }

    @Bean
    public Retryer feignRetryer() {
        Retryer retryer = new Retryer.Default(100, 1000, 4);
        return retryer;
    }
}
