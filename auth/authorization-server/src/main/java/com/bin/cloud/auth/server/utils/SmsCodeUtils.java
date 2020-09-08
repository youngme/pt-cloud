package com.bin.cloud.auth.server.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/7/10 18:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsCodeUtils {
    private int length = 6;
    private int expireIn = 60;
    private String url = "/user1";
}
