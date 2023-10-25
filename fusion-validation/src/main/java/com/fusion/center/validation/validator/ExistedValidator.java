package com.fusion.center.validation.validator;


import cn.hutool.extra.spring.SpringUtil;
import com.fusion.center.common.utils.MapUtils;
import com.fusion.center.common.utils.ReflectUtils;
import com.fusion.center.validation.ExistProvider;
import com.fusion.center.validation.annotation.Existed;
import org.springframework.util.Assert;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ExistedValidator.
 */
public class ExistedValidator implements ConstraintValidator<Existed, Serializable> {

    /**
     * target annotation.
     */
    private Existed annotation;

    /**
     * provider cache.
     */
    private final Map<String, ExistProvider> providerCacheMap = new ConcurrentHashMap<>();

    @Override
    public void initialize(final Existed constraintAnnotation) {
        annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(final Serializable value, final ConstraintValidatorContext context) {
        Assert.notNull(annotation.provider(), "the validation ExistProvider is not found");

        if (annotation.nullOfIgnore() && Objects.isNull(value)) {
            // null of ignore
            return true;
        }
        if (annotation.reverse()) {
            return !doValid(value);
        }
        return doValid(value);
    }

    private ExistProvider getExistProvider() {
        return MapUtils.computeIfAbsent(providerCacheMap, annotation.provider().getName(), key -> SpringUtil.getBean(annotation.provider()));
    }

    private Boolean doValid(final Serializable value) {
        Object provider = getExistProvider();
        // custom providerMethod
        if (!Existed.EXISTED.equals(annotation.providerMethodName())) {
            return Boolean.TRUE.equals(ReflectUtils.invokeMethod(provider, annotation.providerMethodName(), new RuntimeException("the validation ExistProviderMethod invoked error"), value));
        }
        return Boolean.TRUE.equals(getExistProvider().existed(value));
    }
}
