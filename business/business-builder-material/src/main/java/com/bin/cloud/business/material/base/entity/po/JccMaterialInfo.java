package com.bin.cloud.business.material.base.entity.po;

import com.bin.cloud.common.core.entity.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

/**
 * @Description 建材信息
 * @Author hubin
 * @Date 2019-09-10 10:41
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Alias("jccMaterialInfo")
public class JccMaterialInfo extends BasePo {
    private String title;           // 标题
    private String businessName;    // 商家名称
    private String price;       // 价格
    private String content;         // 内容
    private String city;            // 市
    private String district;        // 区
    private String county;          // 县
    private String town;            // 镇
    private String address;         // 详细地址
    private String phone;           // 手机号
    private String imgId;           // 图片ID
    private String description;     // 描述
    private String contact;         // 联系人
}
