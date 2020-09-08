package com.bin.cloud.system.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <br>
 *
 * @author hubin
 * @title: 系统fegin
 * @description:
 * @date: 2019/6/27 14:58
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SystemWebMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SystemWebMain.class).run(args);
    }
}
