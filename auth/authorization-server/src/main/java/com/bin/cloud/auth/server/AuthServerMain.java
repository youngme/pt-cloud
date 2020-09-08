package com.bin.cloud.auth.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/6/27 16:04
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AuthServerMain {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AuthServerMain.class).run(args);
    }
}
