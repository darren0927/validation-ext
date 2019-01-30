package com.darren.validation.ext;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * 参数验证注解扩展：MultipleInt 允许参数设置为指定的多个int值
 * @author tandong
 * @Description:TODO
 * @date 2019/1/30 14:52
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Constraint(validatedBy = ListSizeConstraintValidator.class)
public @interface ListSize {

    int min() default 0;

    int max() default 0;

    String message() default "Invalid value";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
