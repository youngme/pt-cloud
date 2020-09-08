package com.bin.cloud.auth.authentication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <br>
 *
 * @author hubin
 * @title: 鉴权
 * @description:
 * @date: 2019/6/28 17:05
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthenticationServerMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AuthenticationServerMain.class).run(args);
    }
}
