package com.cloud.platform.web.configuration;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.cloud.platform.common.response.BaseResponse;
import com.cloud.platform.common.utils.JsonUtil;
import com.cloud.platform.web.aop.LoggerHandler;
import com.cloud.platform.web.aop.NewAuthHandler;
import com.cloud.platform.web.filter.LogWithUUIDFilter;
import com.cloud.platform.web.properties.CloudWebProperties;
import com.cloud.platform.web.utils.ExceptionUtils;
import com.github.dozermapper.spring.DozerBeanMapperFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Configuration
@EnableConfigurationProperties({ CloudWebProperties.class })
@Import({ SwaggerConfiguration.class })
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
    @ConditionalOnMissingBean
    @ConditionalOnProperty(
            prefix = "cloud.web",
            name = {"enableNewAuth"},
            matchIfMissing = true
    )
    public NewAuthHandler getNewAuthHandler() {
        return new NewAuthHandler();
    }

    @Bean
    @ConditionalOnProperty(
            prefix = "cloud.web",
            name = {"enableHttpMessageConverters"},
            matchIfMissing = true
    )
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //第二个是用来指定当属性值为null是是否输出
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastJsonHttpMessageConverter);
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
        return jsonConverter;
    }


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass
    @ConditionalOnProperty(
            prefix = "enmonster.web",
            name = {"enableDozer"},
            matchIfMissing = true
    )
    public DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean(@Value("classpath*:dozen/*mapper.xml") Resource[] resources) throws IOException {
        DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean = new DozerBeanMapperFactoryBean();
        dozerBeanMapperFactoryBean.setMappingFiles(resources);
        return dozerBeanMapperFactoryBean;
    }


    @Bean
    public FilterRegistrationBean logWithUUIDFilter(CloudWebProperties cloudWebProperties) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LogWithUUIDFilter(cloudWebProperties));
        registration.addUrlPatterns(new String[]{"/*"});
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
            return ExceptionUtils.logAndResponse(request.getRequestURI(), ex, handler.getResponseType(), handler.getDefaultResponse());
        }
    }

}
