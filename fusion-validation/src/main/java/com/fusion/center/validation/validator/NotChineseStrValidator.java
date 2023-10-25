package com.fusion.center.validation.validator;


import com.fusion.center.validation.annotation.NotChineseStr;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 中文校验
 * @author haoxd
 */
public class NotChineseStrValidator implements ConstraintValidator<NotChineseStr, String> {

    private static final Pattern p = Pattern.compile("[\u4e00-\u9fa5]");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {


        Matcher m = p.matcher(value);
        return !m.find();
    }
}