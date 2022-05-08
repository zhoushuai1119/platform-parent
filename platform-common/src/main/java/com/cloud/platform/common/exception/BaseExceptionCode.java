package com.cloud.platform.common.exception;

/**
 * @Description:
 * @Author: ZhouShuai
 * @Date: 2021-06-27 16:24
 */
public interface BaseExceptionCode {

    /**
     * 错误码
     * @return
     */
    String getErrorCode();

    /**
     * 错误信息
     * @return
     */
    String getErrorMessage();

}
