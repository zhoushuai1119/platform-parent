package com.cloud.platform.web.dozeConverter;

import com.github.dozermapper.core.DozerConverter;

import java.time.LocalDate;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/4/2 18:44
 * @version: v1
 */
public class LocalDateToLocalDateDozerConverter extends DozerConverter<LocalDate, LocalDate> {

    public LocalDateToLocalDateDozerConverter() {
        super(LocalDate.class, LocalDate.class);
    }

    public LocalDate convertFrom(LocalDate source, LocalDate destination) {
        return source;
    }

    public LocalDate convertTo(LocalDate source, LocalDate destination) {
        return source;
    }

}
