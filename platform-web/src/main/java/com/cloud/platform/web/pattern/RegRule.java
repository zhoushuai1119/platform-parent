package com.cloud.platform.web.pattern;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/4/3 14:10
 * @version: v1
 */
public interface RegRule {

    /**
     * 校验手机号格式
     */
    String MOBILE = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$";

}
