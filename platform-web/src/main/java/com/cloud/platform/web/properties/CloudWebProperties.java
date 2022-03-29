package com.cloud.platform.web.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/3/29 17:33
 * @version: v1
 */
@Data
@ConfigurationProperties(
    prefix = "cloud.web"
)
public class CloudWebProperties {

    private boolean enableMethodLogger = true;
    private boolean enableLogWithUUID = false;
    private boolean enableJacksonConverter = true;
    private boolean enableDozer = true;
    private CloudWebProperties.GlobalExceptionHandler globalExceptionHandler = new CloudWebProperties.GlobalExceptionHandler();

    @Data
    @NoArgsConstructor
    public static final class GlobalExceptionHandler {
        public static final String RESPONSE_NONE = "none";
        public static final String RESPONSE_SHORT = "short";
        boolean enabled = true;
        String responseType = "none";
        String defaultResponse = "system error";
    }

}
