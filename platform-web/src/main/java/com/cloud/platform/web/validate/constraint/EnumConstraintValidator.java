package com.cloud.platform.web.validate.constraint;

import com.cloud.platform.web.aop.annotation.EnumCheck;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @description: 枚举值校验
 * @author: zhou shuai
 * @date: 2022/4/3 14:21
 * @version: v1
 */
public class EnumConstraintValidator implements ConstraintValidator<EnumCheck, Object> {

    /**
     * 注解对象
     */
    private EnumCheck annotation;

    /**
     * 初始化方法
     *
     * @param constraintAnnotation 注解对象
     */
    @Override
    public void initialize(EnumCheck constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (Objects.isNull(value) || !annotation.clazz().isEnum()) {
            return false;
        }

        Object[] enumConstants = annotation.clazz().getEnumConstants();
        try {
            Method method = annotation.clazz().getMethod(annotation.method());
            for (Object enumConstant : enumConstants) {
                if (value.equals(method.invoke(enumConstant))) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
