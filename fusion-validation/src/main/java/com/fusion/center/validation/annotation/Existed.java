
package com.fusion.center.validation.annotation;




import com.fusion.center.validation.ExistProvider;
import com.fusion.center.validation.validator.ExistedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * . check object is Existed
 * @author haoxd
 **/
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(Existed.List.class)
@Documented
@Constraint(validatedBy = ExistedValidator.class)
public @interface Existed {

    /**
     * default provider method name.
     */
    String EXISTED = "existed";
    /**
     * if null,valid is ignore.
     *
     * @return not existed
     */
    boolean nullOfIgnore() default false;
    
    /**
     * if reverse ,it is not existed.
     *
     * @return not existed
     */
    boolean reverse() default false;
    
    /**
     * message.
     *
     * @return string
     */
    String message() default "the key is not existed!";
    
    /**
     * existed provider.
     *
     * @return class
     */
    Class<? extends ExistProvider> provider();

    /**
     * existed provider.
     *
     * @return class
     */
    String providerMethodName() default EXISTED;

    /**
     * support groups.
     *
     * @return class array.
     */
    Class<?>[] groups() default {};
    
    /**
     * payload.
     *
     * @return Payload class array
     */
    Class<? extends Payload>[] payload() default {};
    
    
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        
        Existed[] value();
    }
}
