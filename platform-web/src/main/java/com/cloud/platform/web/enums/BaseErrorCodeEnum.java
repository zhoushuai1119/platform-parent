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
    JSON_PARSER_ERROR("100003", "json parse error");

    private String code;
    private String message;

}
