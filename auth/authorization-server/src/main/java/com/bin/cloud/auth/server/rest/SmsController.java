package com.bin.cloud.auth.server.rest;

import com.bin.cloud.auth.server.service.IMobileCodeManagerService;
import com.bin.cloud.auth.server.service.ISmsCodeSenderService;
import com.bin.cloud.common.core.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author hubin
 * @title: 验证码发送
 * @description:
 * @date: 2019/7/10 16:45
 */
@RestController
@Slf4j
public class SmsController {

    @Value("${sms.mobile.key}")
    private String mobileKey; // 请求中，参数为mobile

    @Resource
    private ISmsCodeSenderService smsCodeSender;
    @Resource
    private IMobileCodeManagerService mobileCodeManagerService;

    @PostMapping("/sms/code")
    public Result createSmsCodeSender(@RequestBody HashMap<String,String> map) {
        String code = mobileCodeManagerService.generateCode();
        // 保存手机及验证码到redis
        mobileCodeManagerService.saveCode(map.get("mobile"), code, 5, TimeUnit.MINUTES);
        smsCodeSender.send(map.get("mobile"), code);
        return Result.success("发送成功");
    }
}
