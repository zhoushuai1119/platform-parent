package com.cloud.platform.web.configuration;

import com.cloud.platform.common.domain.response.BaseResponse;
import com.cloud.platform.common.utils.JsonUtil;
import com.cloud.platform.web.aop.LoggerHandler;
import com.cloud.platform.web.filter.LogWithUUIDFilter;
import com.cloud.platform.web.properties.CloudWebProperties;
import com.cloud.platform.web.utils.GlobalExceptionUtils;
import com.github.dozermapper.spring.DozerBeanMapperFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/3/29 17:19
 * @version: v1
 */
@Configuration(
     proxyBeanMethods = false
)
@EnableConfigurationProperties({CloudWebProperties.class})
@Import(SwaggerConfiguration.class)
@Slf4j
public class CloudWebAutoConfiguration {

    public CloudWebAutoConfiguration() {

    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(
            prefix = "cloud.web",
            name = {"enableMethodLogger"},
            matchIfMissing = true
    )
    public LoggerHandler getLoggerHandler() {
        return new LoggerHandler();
    }


    @Bean
    @ConditionalOnProperty(
            prefix = "cloud.web",
            name = {"enableJacksonConverter"},
            matchIfMissing = true
    )
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(JsonUtil.OBJECT_MAPPER);
        //设置中文编码格式
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON);
        jsonConverter.setSupportedMediaTypes(list);
        return jsonConverter;
    }


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass
    @ConditionalOnProperty(
            prefix = "cloud.web",
            name = {"enableDozer"},
            matchIfMissing = true
    )
    public DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean(@Value("classpath*:dozen/*mapper.xml") Resource[] resources) throws IOException {
        DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean = new DozerBeanMapperFactoryBean();
        dozerBeanMapperFactoryBean.setMappingFiles(resources);
        return dozerBeanMapperFactoryBean;
    }


    /**
     * 配置跨域请求
     * @return
     */
    @Bean
    @ConditionalOnProperty(
            prefix = "cloud.web",
            name = {"enableCors"},
            matchIfMissing = true
    )
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许跨域的域名， *表示允许任何域名使用
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        //带上cookie信息
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }


    @Bean
    @ConditionalOnProperty(
            prefix = "cloud.web",
            name = {"enableLogWithUUID"}
    )
    public FilterRegistrationBean logWithUUIDFilter(CloudWebProperties cloudWebProperties) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LogWithUUIDFilter(cloudWebProperties));
        registration.addUrlPatterns("/*");
        registration.setName("logWithUUIDFilter");
        registration.setOrder(1);
        return registration;
    }

    @ControllerAdvice
    @ConditionalOnProperty(
            prefix = "cloud.web.globalExceptionHandler",
            name = {"enabled"},
            matchIfMissing = true
    )
    public static final class GlobalExceptionHandler {

        @Autowired
        private CloudWebProperties cloudProperties;

        public GlobalExceptionHandler() {
        }

        @ExceptionHandler({Exception.class})
        @ResponseBody
        BaseResponse<Object> handleControllerException(HttpServletRequest request, Throwable ex) {
            CloudWebProperties.GlobalExceptionHandler handler = this.cloudProperties.getGlobalExceptionHandler();
            return GlobalExceptionUtils.logAndResponse(request.getRequestURI(), ex, handler.getResponseType(), handler.getDefaultResponse());
        }
    }

}
