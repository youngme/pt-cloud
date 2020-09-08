package com.bin.cloud.business.material.base.entity.po;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-05-19 10:02
 * @Version 1.0
 **/
@Data
public class CollectInfo {
    private Long id = 0L;
    private Integer type;        // 类型
    private Long pId;            // 帖子ID
    private Long userId;         // 用户ID
    private Date createdTime = Date.from(ZonedDateTime.now().toInstant());
}
