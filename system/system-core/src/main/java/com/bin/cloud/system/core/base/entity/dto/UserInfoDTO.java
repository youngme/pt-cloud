package com.bin.cloud.system.core.base.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-10-21 15:24
 * @Version 1.0
 **/
@Data
public class UserInfoDTO {
    private Long id;
    private String nickname;
    private String mobile;
    private String username;
    private String password;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private Boolean accountNonLocked;
    private String  headimgurl;
    private String  sex;
    private String createdBy;
    private String updatedBy;
    private Date createdTime;
    private Date updatedTime;
    private String  openid;      // 第三方openid
}
