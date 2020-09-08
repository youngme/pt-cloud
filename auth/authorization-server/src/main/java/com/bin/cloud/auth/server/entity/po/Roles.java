package com.bin.cloud.auth.server.entity.po;

import com.bin.cloud.common.core.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true )
@NoArgsConstructor
@AllArgsConstructor
public class Roles extends BasePo {
    private String code;
    private String name;
    private String description;
}
