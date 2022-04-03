package com.cloud.platform.web.dozeConverter;

import com.github.dozermapper.core.DozerConverter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/4/2 18:44
 * @version: v1
 */
public class StringToLocalDateConverter extends DozerConverter<String, LocalDate> {

    public StringToLocalDateConverter() {
        super(String.class, LocalDate.class);
    }

    public LocalDate convertTo(String source, LocalDate target) {
        if (StringUtils.isNotBlank(source)) {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return null;
    }

    public String convertFrom(LocalDate source, String target) {
        if (source != null) {
            return source.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return null;
    }

}
