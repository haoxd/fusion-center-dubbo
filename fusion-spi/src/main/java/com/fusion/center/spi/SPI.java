

package com.fusion.center.spi;

import java.lang.annotation.*;

/**
 * @author haoxd
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SPI {

    /**
     * value
     * @return value
     */
    String value() default "";
}
