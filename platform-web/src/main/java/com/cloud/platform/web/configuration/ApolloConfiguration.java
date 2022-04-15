package com.cloud.platform.web.configuration;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @description: ConfigurationProperties配置实现自动更新
 * ConfigurationProperties如果需要在Apollo配置变化时自动更新注入的值，需要配合使用EnvironmentChangeEvent或RefreshScope
 * @author: zhou shuai
 * @date: 2022/4/14 20:11
 * @version: v1
 */
@ConditionalOnClass({ ConfigChange.class })
@Configuration
@Slf4j
public class ApolloConfiguration implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @ApolloConfigChangeListener
    public void onConfigChange(ConfigChangeEvent changeEvent) {
        changeEvent.changedKeys().stream().map(changeEvent::getChange).forEach(change -> {
            log.info("Found change - key: {}, oldValue: {}, newValue: {}, changeType: {}", change.getPropertyName(),
                    change.getOldValue(), change.getNewValue(), change.getChangeType());
            //更新相应的bean的属性值，主要是存在@ConfigurationProperties注解的bean
            this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
