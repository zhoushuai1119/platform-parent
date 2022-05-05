package com.cloud.platform.web.enums;

import com.cloud.platform.common.exception.BaseExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/3/29 16:04
 * @version: v1
 */
@AllArgsConstructor
@Getter
public enum BaseErrorCodeEnum implements BaseExceptionCode {

    SUCCESS("000000", "success"),
    SYSTEM_ERROR("100000", "system error"),
    PARAM_ERROR("100001", "param error"),
    JSON_PARSER_ERROR("100002", "json parse error"),
    FEIGN_CLIENT_ERROR("100003", "feign invoke fail"),
    CONVERSION_FAILED_ERROR("100004", "conversion failed exception"),
    ILLEGAL_ARGUMENT_ERROR("100005", "illegal argument exception"),
    TOKEN_VERIFICATION_ERROR("100006", "token  verification exception")
    ;

    private String code;
    private String message;

    @Override
    public String getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }

    @Override
    public String getErrorTips() {
        return message;
    }

}
