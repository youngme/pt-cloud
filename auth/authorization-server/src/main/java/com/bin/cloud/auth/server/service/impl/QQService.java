package com.bin.cloud.auth.server.service.impl;

import com.bin.cloud.auth.server.entity.vo.QQUserInfo;
import com.bin.cloud.auth.server.service.IQQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-08-17 10:37
 * @Version 1.0
 **/
@Slf4j
public class QQService extends AbstractOAuth2ApiBinding implements IQQService {
    @Override
    public QQUserInfo getUserInfo() {
        return null;
    }
}
