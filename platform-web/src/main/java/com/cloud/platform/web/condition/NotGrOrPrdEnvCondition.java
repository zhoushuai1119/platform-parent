package com.cloud.platform.web.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/3/29 17:43
 * @version: v1
 */
public class NotGrOrPrdEnvCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        final Environment environment = context.getEnvironment();
        return !environment.acceptsProfiles(Profiles.of("gr","prod"));
    }

}
