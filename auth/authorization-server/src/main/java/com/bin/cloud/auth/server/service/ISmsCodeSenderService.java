package com.bin.cloud.auth.server.service;

/**
 * <br>
 *
 * @author hubin
 * @title: 发送验证码
 * @description:
 * @date: 2019/7/10 10:37
 */
public interface ISmsCodeSenderService {
    void send(String mobile,String code);
}
