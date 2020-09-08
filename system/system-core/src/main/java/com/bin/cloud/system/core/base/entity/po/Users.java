package com.bin.cloud.system.core.base.entity.po;

import com.bin.cloud.common.core.entity.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@EqualsAndHashCode(callSuper = true )
@NoArgsConstructor
@Alias("users")
public class Users extends BasePo {
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
}