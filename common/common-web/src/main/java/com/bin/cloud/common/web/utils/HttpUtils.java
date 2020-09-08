package com.bin.cloud.common.web.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-10-22 15:08
 * @Version 1.0
 **/
@Slf4j
public class HttpUtils {

    // body 参数解析器
    public static Map<String,Object> httpBodyParser(HttpServletRequest request){
        Map<String,Object> params = new HashMap<>();
        BufferedReader br;
        try {
            br = request.getReader();
            String str, wholeStr = "";
            while((str = br.readLine()) != null){
                wholeStr += str;
            }
            if(StringUtils.isNotEmpty(wholeStr)){
                params = JSON.parseObject(wholeStr,Map.class);
            }
        } catch (IOException e) {
            log.error("解析失败："+e);
        }
        return params;
    }
}
