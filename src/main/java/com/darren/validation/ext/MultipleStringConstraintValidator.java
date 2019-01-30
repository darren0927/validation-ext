package com.darren.validation.ext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * @author darren
 * @Description:参数约束校验器：MultipleString 允许参数设置为指定的多个string值
 * @date 2019/1/30 14:52
 */
public class MultipleStringConstraintValidator implements ConstraintValidator<MultipleString, String> {

    /**
     * 注解中设置的允许的默认值
     */
    private String[] values;

    /**
     * 注解中设置的错误提示信息
     */
    private String msg;

    @Override
    public void initialize(MultipleString constraintAnnotation) {
        this.values = constraintAnnotation.values();
        this.msg = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean isEmpty = (null == value || "".equals(value));
        /**
         * 需要校验的参数不为空，且参数值在允许的值中，则校验通过
         */
        if(!isEmpty && Arrays.asList(values).contains(value)){
            return true;
        }
        /**
         * 返回参数校验失败，并构建错误提示信息
         */
        context.disableDefaultConstraintViolation();
        ConstraintValidatorContext.ConstraintViolationBuilder builder =
                context.buildConstraintViolationWithTemplate(msg);
        builder.addConstraintViolation();
        return false;
    }
}
