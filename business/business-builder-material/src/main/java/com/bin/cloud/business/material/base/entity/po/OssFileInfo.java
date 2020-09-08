package com.bin.cloud.business.material.base.entity.po;

import com.bin.cloud.common.core.entity.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description OSS图片服务
 * @Author hubin
 * @Date 2019-09-18 14:20
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OssFileInfo extends BasePo {
    private String imgUrl;
    private String imgKey;
}
