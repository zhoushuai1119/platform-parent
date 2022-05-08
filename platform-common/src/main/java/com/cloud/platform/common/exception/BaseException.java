package com.cloud.platform.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Description:
 * @Author: ZhouShuai
 * @Date: 2021-06-27 16:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -3962157388406613297L;

    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误提示
     */
    private String errorTips;


    public BaseException(String errorCode, String errorMessage, String errorTips) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorTips = errorTips;
    }


    public BaseException(BaseExceptionCode baseErrorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = baseErrorCode.getErrorCode();
        this.errorTips = baseErrorCode.getErrorMessage();
    }

}
