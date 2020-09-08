package com.bin.cloud.business.material.base.entity.query;

import com.bin.cloud.common.core.entity.dto.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-05-19 10:53
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class CollectPageQuery extends PageParam {
    private static final long serialVersionUID = -2386388991512438886L;

    private Long userId;

    @Override
    public CollectPageQuery build() {
        super.build();
        return this;
    }
}
