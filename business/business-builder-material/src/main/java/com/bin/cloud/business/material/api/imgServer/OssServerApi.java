package com.bin.cloud.business.material.api.imgServer;

import com.bin.cloud.business.material.api.ApiConstant;
import com.bin.cloud.business.material.base.service.OssFileInfoService;
import com.bin.cloud.common.core.entity.dto.ImgDTO;
import com.bin.cloud.common.core.entity.vo.Result;
import com.bin.cloud.common.web.utils.OssFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-09-11 13:40
 * @Version 1.0
 **/
@RestController
public class OssServerApi {

    @Resource
    private OssFileInfoService ossFileInfoService;

    @PostMapping(ApiConstant.IMG_SERVER_UPLOAD)
    public Result uploadImg(@RequestParam(value = "file", required = false) MultipartFile file) {
        ImgDTO imgDTO = OssFileUtil.baseImgUpload(file);
        return ossFileInfoService.save(imgDTO);
    }
}
