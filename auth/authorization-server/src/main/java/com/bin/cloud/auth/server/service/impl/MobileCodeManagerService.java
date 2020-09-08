package com.bin.cloud.auth.server.service.impl;
import com.bin.cloud.auth.server.service.IMobileCodeManagerService;
import com.bin.cloud.auth.server.utils.SmsCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author hubin
 * @title: 手机验证码
 * @description:
 * @date: 2019/7/10 14:26
 */
@Service
@Slf4j
public class MobileCodeManagerService implements IMobileCodeManagerService {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 生成验证码
     * @return
     */
    @Override
    public String generateCode() {
        String code = RandomStringUtils.randomNumeric(new SmsCodeUtils().getLength());
        return code;
    }

    /**
     *  保存验证码
     * @param mobile 手机号
     * @param code  验证码
     * @param expireTime 过期时间
     * @param timeUnit 时间单位
     */
    @Override
    public void saveCode(String mobile, String code, long expireTime, TimeUnit timeUnit) {
        try {
            stringRedisTemplate.opsForValue().set(mobile, code, expireTime, timeUnit);
        } catch (Exception ex) {
            log.error("手机验证码保存失败，保存失败手机号:{}，验证码:{}", mobile, code);
            ex.printStackTrace();
        }
    }

    /**
     * 获取验证码
     * @param mobile
     * @return
     */
    @Override
    public String getCode(String mobile) {
        return stringRedisTemplate.opsForValue().get(mobile);
    }

    /**
     * 删除验证码
     * @param mobile
     */
    @Override
    public void removeCode(String mobile) {

        if (stringRedisTemplate.hasKey(mobile)) {
            stringRedisTemplate.delete(mobile);
            log.info("手机号为{},验证码删除成功！", mobile);
        }
    }
}
