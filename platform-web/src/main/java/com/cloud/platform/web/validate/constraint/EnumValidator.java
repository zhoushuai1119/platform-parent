package com.cloud.platform.web.validate.constraint;

import java.io.Serializable;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/4/3 14:21
 * @version: v1
 */
public interface EnumValidator<T extends Serializable> {

    T getValue();

}
