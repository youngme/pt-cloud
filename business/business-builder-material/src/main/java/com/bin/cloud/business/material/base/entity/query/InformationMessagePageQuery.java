package com.bin.cloud.business.material.base.entity.query;

import com.bin.cloud.common.core.entity.dto.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-04-12 10:58
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Alias("pageParams")
public class InformationMessagePageQuery extends PageParam {
    private static final long serialVersionUID = 8474319895533951470L;
    private String type;            // 类型

    @Override
    public InformationMessagePageQuery build() {
        super.build();
        return this;
    }
}
