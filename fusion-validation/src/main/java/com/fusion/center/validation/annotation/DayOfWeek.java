package com.fusion.center.validation.annotation;


import com.fusion.center.validation.validator.DayOfWeekValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DayOfWeekValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DayOfWeek {
    String message() default "Unknown day of week";
    Class[] groups() default {};
    Class[] payload() default {};
}