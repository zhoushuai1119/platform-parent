package com.cloud.platform.common.utils.dozeConverter;

import com.github.dozermapper.core.DozerConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/4/2 18:44
 * @version: v1
 */
public class StringToLocalDateDozerConverter extends DozerConverter<String, LocalDateTime> {

    public StringToLocalDateDozerConverter() {
        super(String.class, LocalDateTime.class);
    }

    public LocalDateTime convertTo(String source, LocalDateTime target) {
        return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String convertFrom(LocalDateTime source, String target) {
        return source.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
