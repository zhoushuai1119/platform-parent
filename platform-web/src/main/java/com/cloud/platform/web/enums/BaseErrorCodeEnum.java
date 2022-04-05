package com.cloud.platform.web.enums;

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
public enum BaseErrorCodeEnum {

    SUCCESS("000000", "success"),
    SYSTEM_ERROR("100000", "system error"),
    PARAM_ERROR("100001", "param error"),
    JSON_PARSER_ERROR("100002", "json parse error"),
    CONVERSION_FAILED_ERROR("100003", "conversion failed Exception")
    ;

    private String code;
    private String message;

}
