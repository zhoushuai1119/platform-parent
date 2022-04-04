package com.cloud.platform.web.factory;

import com.cloud.platform.web.enums.ConverterBaseEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Objects;

/**
 * @description: 自定义转换器，value 转换为 Enum
 * 需要注意的是，在Spring MVC和Spring Boot中，
 * 由于从客户端接收到的请求都被视为String类型，所以只能用String转枚举的converter。
 * @author: 周帅
 * @date: 2021/1/25 18:59
 * @version: V1.0
 */
public class StringToEnumConverterFactory implements ConverterFactory<String, ConverterBaseEnum> {

    @Override
    public <T extends ConverterBaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return source ->  {
            T obj = null;
            if (StringUtils.isNotBlank(source) && targetType != null) {
                T[] enumConstants = targetType.getEnumConstants();
                for (T enumConstant : enumConstants) {
                    String enumValue = String.valueOf(enumConstant.getValue());
                    if (Objects.equals(enumValue, source)) {
                        obj = enumConstant;
                        break;
                    }
                }
            }
            if (obj == null) {
                // 抛出该异常后，会调用 spring 的默认转换方案，即使用 枚举字面量进行映射
                // org.springframework.core.convert.support.StringToEnumConverterFactory
                throw new IllegalArgumentException("No element matches " + source);
            }
            return obj;
        };
    }

}
