package com.darren.validation.ext;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

/**
 * 参数验证注解扩展：MultipleString 允许参数设置为指定的多个string值
 * @author tandong
 * @Description:TODO
 * @date 2019/1/30 14:52
 */
@Target({ FIELD, PARAMETER, METHOD, ANNOTATION_TYPE, CONSTRUCTOR, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Constraint(validatedBy = MultipleStringConstraintValidator.class)
public @interface MultipleString {

    String[] values() default {};

    String message() default "Invalid value";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
