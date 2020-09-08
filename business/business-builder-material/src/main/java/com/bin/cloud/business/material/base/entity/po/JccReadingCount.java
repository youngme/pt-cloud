package com.bin.cloud.business.material.base.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 阅读数量统计
 * @Author hubin
 * @Date 2020-06-24 08:24
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JccReadingCount {
    private Long id;
    private Long pId;
    private Integer count;
    private Integer type;

    public JccReadingCount(Long pId, Integer type) {
        this.count = 0;
        this.pId = pId;
        this.type = type;
    }
}
