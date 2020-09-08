package com.bin.cloud.system.core.base.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-10-18 14:54
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VipInfoDTO {

    private Long userId;
    private int month;
}
