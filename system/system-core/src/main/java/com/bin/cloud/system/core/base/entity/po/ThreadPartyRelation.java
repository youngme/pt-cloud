package com.bin.cloud.system.core.base.entity.po;

import com.bin.cloud.common.core.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @Description 微信用户关系
 * @Author hubin
 * @Date 2019-10-18 13:37
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Alias("threadPartyRelation")
public class ThreadPartyRelation extends BasePo {
    private String openid;      // 第三方openid
    private Long userId;        // 用户id
}
