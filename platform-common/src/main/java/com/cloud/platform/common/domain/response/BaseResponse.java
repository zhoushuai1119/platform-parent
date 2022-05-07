package com.cloud.platform.common.domain.response;

import com.cloud.platform.common.exception.BaseException;
import com.cloud.platform.common.exception.BaseExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:
 * @Author: ZhouShuai
 * @Date: 2021-06-27 16:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -6276778785868535133L;

    private boolean success = false;
    private String errorCode;
    private String errorMessage;
    private String errorTips;
    private T model;

    public BaseResponse<T> fail(BaseExceptionCode errorCode) {
        this.setSuccess(false);
        this.setErrorCode(errorCode.getErrorCode());
        this.setErrorMessage(errorCode.getErrorMessage());
        this.setErrorTips(errorCode.getErrorTips());
        return this;
    }


    public BaseResponse<T> fail(BaseException baseException) {
        this.setSuccess(false);
        this.setErrorCode(baseException.getErrorCode());
        this.setErrorMessage(baseException.getMessage());
        return this;
    }

    public BaseResponse<T> success(T model) {
        this.setSuccess(true);
        this.setModel(model);
        return this;
    }

    public static <T> BaseResponse<T> createSuccessResult(T model) {
        BaseResponse<T> response = new BaseResponse();
        return response.success(model);
    }

    public static <T> BaseResponse<T> createFailResult(BaseExceptionCode errorCode) {
        BaseResponse<T> response = new BaseResponse();
        return response.fail(errorCode);
    }

    public static <T> BaseResponse<T> createFailResult(BaseException baseException) {
        BaseResponse<T> response = new BaseResponse();
        return response.fail(baseException);
    }

}
