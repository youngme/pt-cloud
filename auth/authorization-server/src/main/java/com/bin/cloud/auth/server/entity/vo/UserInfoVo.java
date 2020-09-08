package com.bin.cloud.auth.server.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description 用户VO
 * @Author hubin
 * @Date 2019-10-21 15:43
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo {
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
