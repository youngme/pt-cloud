package com.bin.cloud.system.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <br>
 *
 * @author hubin
 * @title: 系统用户
 * @description:
 * @date: 2019/7/5 16:59
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SystemCoreMain {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SystemCoreMain.class).run(args);
    }
}
