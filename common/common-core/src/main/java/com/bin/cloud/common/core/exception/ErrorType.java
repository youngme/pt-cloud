package com.bin.cloud.common.core.exception;

/**
 * <br>
 *
 * @author hubin
 * @title: exceptionInfo
 * @description:
 * @date: 2019/6/27 11:12
 */
public interface ErrorType {
    /**
     * 返回code
     *
     * @return
     */
    String getCode();

    /**
     * 返回mesg
     *
     * @return
     */
    String getMesg();
}
