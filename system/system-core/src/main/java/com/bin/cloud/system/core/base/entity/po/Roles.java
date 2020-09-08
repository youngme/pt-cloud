package com.bin.cloud.system.core.base.entity.po;

import com.bin.cloud.common.core.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@EqualsAndHashCode(callSuper = true )
@NoArgsConstructor
@AllArgsConstructor
@Alias("roles")
public class Roles extends BasePo {
    private String code;
    private String name;
    private String description;
}
