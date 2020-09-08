package com.bin.cloud.auth.server.service;

import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author hubin
 * @title: 手机验证码管理
 * @description:
 * @date: 2019/7/10 14:21
 */
public interface IMobileCodeManagerService {

    String generateCode();

    void saveCode(String mobile, String code, long expireTime, TimeUnit unit);

    String getCode(String mobile);

    void removeCode(String mobile);
}
