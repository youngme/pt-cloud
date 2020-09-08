package com.bin.cloud.auth.server.config;

import com.bin.cloud.auth.server.oauth.convert.WecahtMappingHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-08-22 21:54
 * @Version 1.0
 **/
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(6000);
        requestFactory.setReadTimeout(6000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getMessageConverters().add(new WecahtMappingHttpMessageConverter());
        return restTemplate;
    }
}
