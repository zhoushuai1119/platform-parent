package com.cloud.platform.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;


/**
 * @description: 防止雪花算法导致的精度丢失
 * @author: zhou shuai
 * @date: 2022/4/4 13:29
 * @version: v1
 */
public class LongToStringSerializer extends JsonSerializer<Long> {

    @Override
    public void serialize(Long aLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String str = String.valueOf(aLong);
        if (str.length() >= 18) {
            jsonGenerator.writeString(str);
        } else {
            jsonGenerator.writeNumber(aLong);
        }
    }

}
