package com.cloud.platform.web.dozeConverter;

import com.github.dozermapper.core.DozerConverter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/4/2 18:44
 * @version: v1
 */
public class StringToLocalDateTimeConverter extends DozerConverter<String, LocalDateTime> {

    public StringToLocalDateTimeConverter() {
        super(String.class, LocalDateTime.class);
    }

    public LocalDateTime convertTo(String source, LocalDateTime target) {
        if (StringUtils.isNotBlank(source)) {
            return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        return null;
    }

    public String convertFrom(LocalDateTime source, String target) {
        if (source != null) {
            return source.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        return null;
    }

}
