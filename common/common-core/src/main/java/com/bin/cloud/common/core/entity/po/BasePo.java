package com.bin.cloud.common.core.entity.po;

import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * <br>
 *
 * @author hubin
 * @title: 基础Po
 * @description:
 * @date: 2019/6/27 11:09
 */
@Data
public class BasePo implements Serializable {
    public final static String DEFAULT_USERNAME = "system";
    private Long id = 0L;
    private String createdBy = DEFAULT_USERNAME;
    private String updatedBy = DEFAULT_USERNAME;
    private Date createdTime = Date.from(ZonedDateTime.now().toInstant());
    private Date updatedTime = Date.from(ZonedDateTime.now().toInstant());
}
