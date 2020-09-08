package com.bin.cloud.system.core.base.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bin.cloud.common.core.utils.DateUtils;
import com.bin.cloud.system.core.base.dao.UserVipInfoMapper;
import com.bin.cloud.system.core.base.entity.dto.VipInfoDTO;
import com.bin.cloud.system.core.base.entity.po.UserVipInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * @Description 用户会员信息
 * @Author hubin
 * @Date 2019-10-18 14:35
 * @Version 1.0
 **/
@Service
@Slf4j
public class UserVipInfoService extends ServiceImpl<UserVipInfoMapper, UserVipInfo>{

    public UserVipInfo getVipInfoByUserId(Long userId) {
        return baseMapper.getVipInfoByUserId(userId);
    }

    public boolean dredgeVip(VipInfoDTO vipInfoDTO) {
        UserVipInfo info = this.getVipInfoByUserId(vipInfoDTO.getUserId());
        try {
            if (Objects.nonNull(info)) {
                // 设置会员过期时间 从当下时间算起
                dredgeExecuteProcess(info, vipInfoDTO.getMonth());
                baseMapper.updateById(info);
            }else{
                info = new UserVipInfo();
                dredgeExecuteProcess(info, vipInfoDTO.getMonth());
                info.setUserId(vipInfoDTO.getUserId());
                baseMapper.insert(info);
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("开通失败，失败原因:{}", e.getMessage());
            return Boolean.TRUE;
        }
    }

    /**
     * 开通会员流程
     * @param info
     * @param month
     */
    private void dredgeExecuteProcess(UserVipInfo info,int month){
        info.setDredgeTime(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiresTime(DateUtils.getAddMonthDate(info.getDredgeTime(), month))
                .setVip(1)  // 设置会员开通
                .setUpdatedTime(Date.from(ZonedDateTime.now().toInstant()));
    }
}
