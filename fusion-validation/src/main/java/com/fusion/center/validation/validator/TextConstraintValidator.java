package com.fusion.center.validation.validator;


import com.fusion.center.validation.annotation.TextConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * 文本约束校验
 * @author haoxd
 */
public class TextConstraintValidator implements ConstraintValidator<TextConstraint, String> {

    private String[] allText;


    @Override
    public void initialize(TextConstraint textConstraint) {
        this.allText = textConstraint.values();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return Arrays.asList(allText).contains(value);
    }
}