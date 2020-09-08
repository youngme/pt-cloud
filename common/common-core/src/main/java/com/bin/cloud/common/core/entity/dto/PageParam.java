package com.bin.cloud.common.core.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description 分页参数
 * @Author hubin
 * @Date 2019-07-18 22:08
 * @Version 1.0
 **/
@Data
@Accessors(chain = true)
public class PageParam implements Serializable {

    private static final long serialVersionUID = -8478878574814762301L;
    int limit;
    int page;

    public PageParam build() {
        int size = limit;
        limit = limit * page;
        page = (page - 1) <= 0 ? 0 : (page - 1) * size;
        return this;
    }
}