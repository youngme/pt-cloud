package com.bin.cloud.common.core.exception;

import lombok.Getter;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/6/27 11:13
 */
@Getter
public enum SystemErrorType implements ErrorType {

    SYSTEM_ERROR("-1", "系统异常"),
    SYSTEM_BUSY("000001", "系统繁忙,请稍候再试"),
    FEIGN_TIME_OUT("000401", "请重新登陆"),
    GATEWAY_NOT_FOUND_SERVICE("010404", "服务未找到"),
    GATEWAY_ERROR("010500", "网关异常"),
    GATEWAY_CONNECT_TIME_OUT("010002", "网关超时"),

    ARGUMENT_IS_EMPTY("020000", "请求参数校验不通过"),
    ARGUMENT_NOT_VALID("020001", "请求参数校验不通过"),
    UPLOAD_FILE_SIZE_LIMIT("030000", "上传文件大小超过限制");

    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String mesg;

    SystemErrorType(String code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }
}
