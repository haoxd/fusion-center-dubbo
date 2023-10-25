package com.fusion.center.validation.annotation;


import com.fusion.center.validation.validator.NotChineseStrValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotChineseStrValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotChineseStr {
    String message() default "str cannot chinese";
    Class[] groups() default {};
    Class[] payload() default {};
}