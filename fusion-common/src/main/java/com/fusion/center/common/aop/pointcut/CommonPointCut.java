package com.fusion.center.common.aop.pointcut;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @Description: AOP 基础切点
 * @Author: haoxd
 * @Version: 1.0
 */
public class CommonPointCut {


    @Pointcut("@annotation(com.fusion.center.common.annotation.LogApm)")
    public void withMetricsAnnotation() {
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) ")
    public void controllerBean() {
    }

    @Pointcut("controllerBean() || withMetricsAnnotation())")
    public void scanBean(){

    }

}
