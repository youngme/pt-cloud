package com.bin.cloud.business.material.base.entity.query;

import com.bin.cloud.common.core.entity.dto.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * @Description 建材分页参数
 * @Author hubin
 * @Date 2019-10-10 16:18
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Alias("jccMaterialPageQuery")
public class JccMaterialPageQuery extends PageParam {
    private static final long serialVersionUID = 8474319895533951470L;

    private String priceStart;      // 价钱开始
    private String priceEnd;        // 价钱结束
//    private String city;            // 城市
//    private String district;        // 区
//    private String county;          // 县
    private String town;            // 镇
    private String type;            // 类型

    @Override
    public JccMaterialPageQuery build() {
        super.build();
        return this;
    }
}
