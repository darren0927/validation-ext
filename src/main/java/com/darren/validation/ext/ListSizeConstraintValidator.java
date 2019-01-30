package com.darren.validation.ext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author darren
 * @Description:参数约束校验器：MultipleInt 允许参数设置为指定的多个int值
 * @date 2019/1/30 14:52
 */
public class ListSizeConstraintValidator implements ConstraintValidator<ListSize, List> {

    /**
     * 注解中设置的允许的默认值
     */
    private int min;

    private int max;

    /**
     * 注解中设置的错误提示信息
     */
    private String msg;

    @Override
    public void initialize(ListSize constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.msg = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext context) {
        if(null != list && list.size() >= min && list.size() <= max){
            return true;
        }

        /**
         * 返回参数校验失败，并构建错误提示信息
         */
        context.disableDefaultConstraintViolation();
        ConstraintValidatorContext.ConstraintViolationBuilder builder = context.buildConstraintViolationWithTemplate(msg);
        builder.addConstraintViolation();
        return false;
    }
}
