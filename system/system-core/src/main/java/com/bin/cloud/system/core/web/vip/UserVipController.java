package com.bin.cloud.system.core.web.vip;

import com.bin.cloud.common.core.entity.vo.Result;
import com.bin.cloud.system.core.base.entity.dto.VipInfoDTO;
import com.bin.cloud.system.core.base.service.UserVipInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 用户VIP信息
 * @Author hubin
 * @Date 2019-10-18 14:44
 * @Version 1.0
 **/
@RestController
public class UserVipController {

    @Resource
    private UserVipInfoService userVipInfoService;

    @PostMapping("/dredge/vip")
    public Result dredgeVip(@RequestBody VipInfoDTO vipInfo) {
        return userVipInfoService.dredgeVip(vipInfo)?Result.success():Result.failMessage("开通失败");
    }
}
