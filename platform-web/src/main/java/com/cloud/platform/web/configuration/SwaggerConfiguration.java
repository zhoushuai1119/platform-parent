package com.cloud.platform.web.configuration;

import com.cloud.platform.web.condition.NotGrOrPrdEnvCondition;
import com.cloud.platform.web.properties.SwaggerProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/3/29 17:42
 * @version: v1
 */
@Conditional({NotGrOrPrdEnvCondition.class})
@ConditionalOnClass({Docket.class})
@ConditionalOnProperty(
        prefix = "cloud.web.swagger",
        name = {"enabled"},
        matchIfMissing = true
)
@EnableSwagger2
@EnableConfigurationProperties({SwaggerProperties.class})
public class SwaggerConfiguration {

    @Value("${spring.application.name:APP}")
    private String appName;

    @Bean
    public Docket createRestApi(final SwaggerProperties swaggerProperties) {
        final Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(this.apiInfo(swaggerProperties));
        final ApiSelectorBuilder select = docket.select();
        if (swaggerProperties.getBasePackage() != null) {
            select.apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()));
        } else {
            select.apis(RequestHandlerSelectors.any());
        }

        select.build();
        return docket;
    }

    private ApiInfo apiInfo(final SwaggerProperties swaggerProperties) {
        String title = swaggerProperties.getTitle();
        if (title == null) {
            title = this.appName;
        }

        return new ApiInfoBuilder()
                .title(title)
                .description(swaggerProperties.getDescription())
                .termsOfServiceUrl(swaggerProperties.getServiceUrl())
                .version(swaggerProperties.getVersion())
                .build();
    }

}
