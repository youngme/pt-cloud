package com.bin.cloud.business.material.api.information.context;

import com.bin.cloud.business.material.base.entity.po.InformationMessage;
import com.bin.cloud.business.material.base.entity.query.InformationMessagePageQuery;
import com.bin.cloud.business.material.base.entity.query.InformationMessageQuery;
import com.bin.cloud.business.material.base.entity.vo.InformationMessageVo;
import com.bin.cloud.business.material.base.service.InformationMessageInfoService;
import com.bin.cloud.business.material.base.service.InformationMessageService;
import com.bin.cloud.common.core.entity.vo.PageVo;
import com.bin.cloud.common.core.entity.vo.Result;
import com.bin.cloud.common.core.utils.DataConvert;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2020-04-12 10:40
 * @Version 1.0
 **/
@Service
public class InformationMessageContext {

    @Resource
    private InformationMessageService service;

    @Resource
    private InformationMessageInfoService infoService;

    public Result page(InformationMessagePageQuery pageQuery) {
        try {
            PageVo pageVo = service.queryPageInfo(pageQuery);
            List<InformationMessageVo> voList = pageVo.getRows().stream().map(
                    in ->{
                        InformationMessageVo vo = DataConvert.mergeNotNullReflect(in, InformationMessageVo.class);
                        assert vo != null;
                        vo.setContents(infoService.queryList(vo.getId()));
                        return vo;
                    }
            ).collect(Collectors.toList());
            pageVo.setRows(voList);
            return Result.success(pageVo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failMessage("系统错误");
        }
    }
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Result saveInfo(InformationMessageQuery query) {
        try {
            InformationMessage po = DataConvert.mergeNotNullReflect(query, InformationMessage.class);
            assert po != null;
            service.save(po);
            query.getContents().forEach(content->{
                content.setPjId(po.getId());
                infoService.save(content);
            });
            return Result.success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failMessage("保存失败");
        }
    }

    public Result addReader(HashMap<String, Long> params) {
        if (!params.isEmpty()) {
            Long id = params.get("id");
            assert id != null;
            service.addReadCount(id);
        }
        return Result.success();
    }

    public Result queryInfo(HashMap<String, Long> params) {
        Long id = params.get("id");
        InformationMessage po = service.getById(id);
        InformationMessageVo vo = DataConvert.mergeNotNullReflect(po, InformationMessageVo.class);
        assert vo != null;
        vo.setContents(infoService.queryList(vo.getId()));
        return Result.success(vo);
    }
}
