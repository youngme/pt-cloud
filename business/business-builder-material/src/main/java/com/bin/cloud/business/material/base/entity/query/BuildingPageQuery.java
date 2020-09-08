package com.bin.cloud.business.material.base.entity.query;

import com.bin.cloud.common.core.entity.dto.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-05-08 17:57
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Alias("buildingPageQuery")
public class BuildingPageQuery  extends PageParam {
    private static final long serialVersionUID = 7414817532077954528L;

    private Integer type;

    @Override
    public BuildingPageQuery build() {
        super.build();
        return this;
    }
}
