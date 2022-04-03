package com.cloud.platform.web.dozeConverter;

import com.github.dozermapper.core.DozerConverter;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/4/2 18:43
 * @version: v1
 */
public class LocalDateTimeToLocalDateTimeDozerConverter extends DozerConverter<LocalDateTime, LocalDateTime> {

    public LocalDateTimeToLocalDateTimeDozerConverter() {
        super(LocalDateTime.class, LocalDateTime.class);
    }

    public LocalDateTime convertFrom(LocalDateTime source, LocalDateTime destination) {
        return source;
    }

    public LocalDateTime convertTo(LocalDateTime source, LocalDateTime destination) {
        return source;
    }

}
