package com.bin.cloud.auth.server.rest;

import com.bin.cloud.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @Description 获取当前用户
 * @Author hubin
 * @Date 2019-11-04 15:07
 * @Version 1.0
 **/
@RestController
public class PrincipalController {


    @PostMapping("/online/principal")
    public Result getPrincipal(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        return Result.success();
    }

}
