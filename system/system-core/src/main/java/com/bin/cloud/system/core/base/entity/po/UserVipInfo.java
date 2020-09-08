package com.bin.cloud.system.core.base.entity.po;

import com.bin.cloud.common.core.entity.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Description 会员信息
 * @Author hubin
 * @Date 2019-10-18 14:09
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserVipInfo extends BasePo {
    private int vip;            // 是否是会员（0：过期；1：是会员；-1：不是会员）
    private Date dredgeTime;    // 开通时间
    private Date expiresTime;   // 过期时间
    private Long userId;        // 用户ID
}
