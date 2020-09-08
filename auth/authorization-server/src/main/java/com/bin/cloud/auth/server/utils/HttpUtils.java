package com.bin.cloud.auth.server.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/7/10 15:21
 */
public class HttpUtils {

    //根据key
    public static HashMap<String, String> obtainMobile(HttpServletRequest httpServletRequest) {
        try {
            BufferedReader bufferedReader = httpServletRequest.getReader();
            String readStr;
            StringBuilder bodyInfo = new StringBuilder();
            while (StringUtils.isNotBlank(readStr = bufferedReader.readLine())) {
                bodyInfo.append(readStr);
            }
            if (StringUtils.isBlank(bodyInfo.toString())) {
                return new HashMap<>();
            }
            return JSONObject.parseObject(bodyInfo.toString(), HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
