package com.bin.cloud.common.core.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-09-18 14:35
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
public class ImgDTO {
    private String imgUrl;
    private String imgKey;

    public ImgDTO(String imgUrl, String imgKey) {
        this.imgUrl = imgUrl;
        this.imgKey = imgKey;
    }
}
