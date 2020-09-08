package com.bin.cloud.business.material.api.readingCount.context;

import com.bin.cloud.business.material.base.entity.po.JccReadingCount;
import com.bin.cloud.business.material.base.service.JccReadingCountService;
import com.bin.cloud.common.core.entity.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-06-24 08:34
 * @Version 1.0
 **/
@Service
public class JccReadingCountContext {

    @Resource
    private JccReadingCountService service;


    public void initReadCount(Long pId, Integer type) {
        JccReadingCount readingCount = new JccReadingCount(pId, type);
        service.save(readingCount);
    }

    public JccReadingCount getExist(Long pId, Integer type){
        return service.queryInfo(pId, type);
    }

    public Result updateCount(Map<String, Object> params) {
        Long pId = Long.parseLong(params.get("pId").toString());
        Integer type = (Integer) params.get("type");
        JccReadingCount exist = service.queryInfo(pId, type);
        if (Objects.isNull(exist)) {
            this.initReadCount(pId, type);
        }
        service.updateCount(pId, type);
        return Result.success();
    }


    public Result getCount(Map<String, Object> params) {
        Long pId = (Long) params.get("pId");
        Integer type = (Integer) params.get("type");
        return Result.success(service.getReadingCount(pId, type));
    }

    public Integer getCount(Long pId, Integer type) {
        JccReadingCount exist = this.getExist(pId, type);
        if (Objects.nonNull(exist)) {
            return exist.getCount();
        }else{
            this.initReadCount(pId, type);
        }
        return 0;
    }
}
