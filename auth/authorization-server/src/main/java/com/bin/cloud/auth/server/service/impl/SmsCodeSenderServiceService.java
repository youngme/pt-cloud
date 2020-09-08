package com.bin.cloud.auth.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.bin.cloud.auth.server.service.ISmsCodeSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <br>
 *
 * @author hubin
 * @title: 发送手机验证码
 * @description:
 * @date: 2019/7/10 10:38
 */
@Service
@Slf4j
public class SmsCodeSenderServiceService implements ISmsCodeSenderService {

    @Value("${sms.send.region-id}")
    private String regionId;

    @Value("${sms.send.access-key-id}")
    private String accessKeyId;

    @Value("${sms.send.access-secret}")
    private String accessSecret;

    @Value("${sms.send.version}")
    private String version;

    @Value("${sms.send.do-main}")
    private String doMain;

    @Value("${sms.send.sign-name}")
    private String signName; // 阿里云短信签名

    @Value("${sms.send.template-code}")
    private String templateCode; // 阿里云短信模版CODE

    @Override
    public void send(String mobile, String code) {
        log.info("发送验证码的手机:{},发送的验证码：{}", mobile, code);
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(doMain);
        request.setVersion(version);
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", regionId);
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);

        HashMap<String, String> param = new HashMap<>();
        param.put("code", code);
        String jsonStr = JSON.toJSONString(param);
        request.putQueryParameter("TemplateParam", jsonStr);
        try {
            CommonResponse response = acsClient.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
