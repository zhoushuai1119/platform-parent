package com.cloud.platform.web.dozeConverter;

import com.github.dozermapper.core.DozerConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/4/2 18:42
 * @version: v1
 */
public class DateToLocalDateTimeConverter extends DozerConverter<Date, LocalDateTime>  {

    public DateToLocalDateTimeConverter() {
        super(Date.class, LocalDateTime.class);
    }

    public LocalDateTime convertTo(Date source, LocalDateTime target) {
        Instant instant = source.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public Date convertFrom(LocalDateTime source, Date target) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = source.atZone(zone).toInstant();
        return Date.from(instant);
    }

}
