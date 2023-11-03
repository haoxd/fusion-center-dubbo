

package com.fusion.center.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author haoxd
 */
@Aspect
@Configuration
public class LogApmAspectJ {

    @Resource
    private AroundLogHandler aroundLogHandler;

    /**
     * 切点
     */
    @Pointcut("@annotation(com.fusion.center.common.annotation.LogApmMarker)")
    public void pointcut() {

    }

    /**
     * 执行结果
     *
     * @param joinPoint 切点
     * @return 返回结果
     * @throws Throwable 异常
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return aroundLogHandler.around(new AopAroundLogProxyChainImpl(joinPoint));
    }
}
