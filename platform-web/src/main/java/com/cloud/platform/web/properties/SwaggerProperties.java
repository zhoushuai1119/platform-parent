package com.cloud.platform.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/3/29 17:41
 * @version: v1
 */
@Data
@ConfigurationProperties(prefix = "cloud.web.swagger")
public class SwaggerProperties {

    private boolean enabled;
    private String title;
    private String description;
    private String serviceUrl;
    private String basePackage;
    private String version;

    public SwaggerProperties() {
        this.enabled = true;
        this.version = "1.0";
    }

}
