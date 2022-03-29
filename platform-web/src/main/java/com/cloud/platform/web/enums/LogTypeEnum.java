package com.cloud.platform.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description:
 * @Author: ZhouShuai
 * @Date: 2021-06-27 16:59
 */
@AllArgsConstructor
@Getter
public enum LogTypeEnum {

    PARAM("param log"),
    RETURN("return log"),
    FULL("param and return log");

    private String message;

}
