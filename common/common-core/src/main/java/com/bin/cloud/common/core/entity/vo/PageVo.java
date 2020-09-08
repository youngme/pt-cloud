package com.bin.cloud.common.core.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-07-18 22:19
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
public class PageVo implements Serializable {

    private static final long serialVersionUID = 5967578677791568273L;

    private static  PageVo pageVo;

    private Long total;

    private List<?> rows;

    public static PageVo build() {
        pageVo =new PageVo();
        return pageVo;
    }

    public PageVo total(Long total) {
        pageVo.setTotal(total);
        return pageVo;
    }

    public PageVo rows(List<?> rows) {
        pageVo.setRows(rows);
        return pageVo;
    }

}