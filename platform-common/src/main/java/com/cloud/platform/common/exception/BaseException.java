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

    private String errorCode;
    private String errorTips;
    private BaseExceptionCode baseErrorCode;

    public BaseException(String errorCode, String message, String errorTips) {
        super(message);
        this.errorCode = errorCode;
        this.errorTips = errorTips;
    }

    public BaseException(BaseExceptionCode baseErrorCode, String message) {
        super(message);
        this.baseErrorCode = baseErrorCode;
    }

    public BaseException(BaseExceptionCode baseErrorCode, String message, String errorTips) {
        super(message);
        this.baseErrorCode = baseErrorCode;
        this.errorTips = errorTips;
    }

}
