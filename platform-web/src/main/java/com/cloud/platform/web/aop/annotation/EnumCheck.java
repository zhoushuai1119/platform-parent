package com.cloud.platform.web.aop.annotation;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/4/3 14:18
 * @version: v1
 */

import com.cloud.platform.web.validate.constraint.EnumConstraintValidator;
import com.cloud.platform.web.validate.constraint.EnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = EnumConstraintValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumCheck {

    String message() default "must in enum value range";

    /**
     * @return 指定分组
     */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return 指定枚举类型，参数值必须是这个枚举类型中的值
     */
    Class<? extends EnumValidator> clazz();

    /**
     * 调用的方法名称
     */
    String method() default "getValue";

}
