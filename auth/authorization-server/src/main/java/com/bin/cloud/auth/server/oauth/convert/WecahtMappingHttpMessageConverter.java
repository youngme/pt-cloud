package com.bin.cloud.auth.server.oauth.convert;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 由于微信的请求会存在[text/plain]的response。所以要添加相关的类型支持
 * @Author hubin
 * @Date 2019-08-22 21:49
 * @Version 1.0
 **/
public class WecahtMappingHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public WecahtMappingHttpMessageConverter() {
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.TEXT_PLAIN); //加入text/plain
        mediaTypeList.add(MediaType.TEXT_HTML);  //加入text/html类型的支持
        setSupportedMediaTypes(mediaTypeList);
    }
}
