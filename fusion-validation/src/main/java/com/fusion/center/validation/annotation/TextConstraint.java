package com.fusion.center.validation.annotation;


import com.fusion.center.validation.validator.TextConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Description: 基于标准的jsr303 实现的文本约束 ，判断传入的值是否在 规订的范围当中
 * @Author: haoxd
 * @Version: 1.0
 */
@Documented
@Constraint(validatedBy = TextConstraintValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TextConstraint {

    String message() default "this value not in constraint text";
    String[] values() ;
    Class[] groups() default {};
    Class<? extends Payload>[] payload() default { };
}
