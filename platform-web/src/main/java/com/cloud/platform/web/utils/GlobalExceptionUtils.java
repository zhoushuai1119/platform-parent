package com.cloud.platform.web.utils;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cloud.platform.common.exception.BaseException;
import com.cloud.platform.common.domain.response.BaseResponse;
import com.cloud.platform.common.enums.BaseErrorCodeEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.MDC;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/3/29 20:09
 * @version: v1
 */
@Slf4j
public class GlobalExceptionUtils {

    public GlobalExceptionUtils() {

    }

    public static BaseResponse<Object> logAndResponse(String methodName, Throwable ex, String responseType, String defaultMessage) {
        BaseResponse<Object> baseResponse = new BaseResponse();
        String errorCode = null;
        String errorMessage = null;
        String errorTips = null;
        if (ex instanceof BaseException) {
            BaseException bex = (BaseException) ex;
            errorCode = bex.getErrorCode();
            errorMessage = StringUtils.isEmpty(bex.getMessage()) ? bex.getErrorTips() : bex.getMessage();
            errorTips = bex.getErrorTips();
            log.error("{} method error , code: {}, message: {}", new Object[]{methodName, errorCode, errorMessage});
        } else {
            StringBuilder stringBuilder;
            BindingResult result;
            if (ex instanceof BindException) {
                errorCode = BaseErrorCodeEnum.PARAM_ERROR.getCode();
                result = ((BindException) ex).getBindingResult();
                stringBuilder = new StringBuilder();
                List<FieldError> fieldErrors = result.getFieldErrors();
                if (CollectionUtils.isNotEmpty(fieldErrors)) {
                    fieldErrors.forEach(key -> {
                        stringBuilder.append(key.getField()).append(" ").append(key.getDefaultMessage()).append(", 当前值: '").append(key.getRejectedValue()).append("'; ");
                    });
                }
                errorMessage = stringBuilder.toString();
                errorTips = BaseErrorCodeEnum.PARAM_ERROR.getMessage();
                log.error("{} method error , {}", methodName, errorMessage);
            } else if (ex instanceof MethodArgumentNotValidException) {
                errorCode = BaseErrorCodeEnum.PARAM_ERROR.getCode();
                result = ((MethodArgumentNotValidException) ex).getBindingResult();
                stringBuilder = new StringBuilder();
                List<FieldError> fieldErrors = result.getFieldErrors();
                if (CollectionUtils.isNotEmpty(fieldErrors)) {
                    fieldErrors.forEach(key -> {
                        stringBuilder.append(key.getField()).append(" ").append(key.getDefaultMessage()).append(", 当前值: '").append(key.getRejectedValue()).append("'; ");
                    });
                }
                errorMessage = stringBuilder.toString();
                errorTips = BaseErrorCodeEnum.PARAM_ERROR.getMessage();
                log.error("{} method error , {}", methodName, errorMessage);
            } else if (ex instanceof ConstraintViolationException) {
                errorCode = BaseErrorCodeEnum.PARAM_ERROR.getCode();
                ConstraintViolationException cex = (ConstraintViolationException) ex;
                stringBuilder = new StringBuilder();
                Set<ConstraintViolation<?>> fieldErrors = cex.getConstraintViolations();
                if (CollectionUtils.isNotEmpty(fieldErrors)) {
                    fieldErrors.forEach(key -> {
                        String invald = key.getInvalidValue() != null ? key.getInvalidValue().toString() : "null";
                        stringBuilder.append(key.getPropertyPath()).append(" ").append(key.getMessage()).append(", 当前值: '").append(invald.length() < 50 ? invald : invald.substring(0, 47) + "...").append("'; ");
                    });
                }
                errorMessage = stringBuilder.toString();
                errorTips = BaseErrorCodeEnum.PARAM_ERROR.getMessage();
                log.error("{} method error , {}", methodName, errorMessage);

            } else if (ex.getCause() instanceof JsonProcessingException) {
                errorCode = BaseErrorCodeEnum.JSON_PARSER_ERROR.getCode();
                JsonProcessingException cex = (JsonProcessingException) ex.getCause();
                errorMessage = ExceptionUtils.getMessage(cex);
                errorTips = BaseErrorCodeEnum.JSON_PARSER_ERROR.getMessage();
                log.error("{} method error , {}", methodName, errorMessage);

            } else if (ex instanceof ConversionFailedException) {
                errorCode = BaseErrorCodeEnum.CONVERSION_FAILED_ERROR.getCode();
                ConversionFailedException cex = (ConversionFailedException) ex;
                errorMessage = ExceptionUtils.getMessage(cex);
                errorTips = BaseErrorCodeEnum.CONVERSION_FAILED_ERROR.getMessage();
                log.error("{} method error , {}", methodName, errorMessage);

            }else if (ex instanceof IllegalArgumentException) {
                errorCode = BaseErrorCodeEnum.ILLEGAL_ARGUMENT_ERROR.getCode();
                IllegalArgumentException cex = (IllegalArgumentException) ex;
                errorMessage = ExceptionUtils.getMessage(cex);
                errorTips = BaseErrorCodeEnum.ILLEGAL_ARGUMENT_ERROR.getMessage();
                log.error("{} method error , {}", methodName, errorMessage);
            } else if (ex instanceof JWTVerificationException) {
                errorCode = BaseErrorCodeEnum.TOKEN_VERIFICATION_ERROR.getCode();
                JWTVerificationException cex = (JWTVerificationException) ex;
                errorMessage = ExceptionUtils.getMessage(cex);
                errorTips = BaseErrorCodeEnum.ILLEGAL_ARGUMENT_ERROR.getMessage();
                log.error("{} method error , {}", methodName, errorMessage);
            } else {
                errorCode = BaseErrorCodeEnum.SYSTEM_ERROR.getCode();
                errorMessage = BaseErrorCodeEnum.SYSTEM_ERROR.getMessage();
                log.error(methodName + " method error , ", ex);
            }
        }

        baseResponse.setErrorCode(errorCode);
        baseResponse.setErrorMessage(errorMessage);
        baseResponse.setErrorTips(errorTips);
        baseResponse.setSuccess(false);
        return baseResponse;
    }

}
