
package com.fusion.center.common.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 * @author haoxd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface LogApmMarker {

    /**
     * @return interface name
     */
    String interfaceName() default "";

    /**
     * @return business desc
     */
    String desc() default "";
}
