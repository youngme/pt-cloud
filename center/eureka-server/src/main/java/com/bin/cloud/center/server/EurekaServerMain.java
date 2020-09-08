package com.bin.cloud.center.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <br>
 *
 * @author hubin
 * @title: eureka服务启动
 * @description:
 * @date: 2019/6/27 10:53
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaServerMain.class).run(args);
    }
}
