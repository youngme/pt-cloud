package com.bin.cloud.business.material;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-09-11 16:42
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class JccMaterialMain {

    public static void main(String[] args) {
        new SpringApplicationBuilder(JccMaterialMain.class).run(args);
    }
}
