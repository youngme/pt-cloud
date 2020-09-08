package com.bin.cloud.system.core.base.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @Description 用户VO
 * @Author hubin
 * @Date 2019-10-21 15:43
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("userInfoVo")
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
