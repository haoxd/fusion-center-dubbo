package com.fusion.center.common.aop;


import com.alibaba.fastjson2.JSONObject;
import com.fusion.center.common.annotation.LogApm;
import com.fusion.center.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;


/**
 * 日志追踪切面
 * haoxd
 */
@Aspect
@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LogApmAspect {


    private static final Map<Class<?>, Object> DEFAULT_VALUES = Stream
            .of(boolean.class, byte.class, char.class, double.class, float.class, int.class, long.class, short.class)
            .collect(toMap(clazz -> (Class<?>) clazz, clazz -> Array.get(Array.newInstance(clazz, 1), 0)));

    public static <T> T getDefaultValue(Class<T> clazz) {
        return (T) DEFAULT_VALUES.get(clazz);
    }


    @Around("com.fusion.center.common.aop.pointcut.CommonPointCut.scanBean()) ")
    public Object metrics(ProceedingJoinPoint pjp) throws Throwable {
        //通过连接点获取方法签名和方法上Metrics注解，并根据方法签名生成日志中要输出的方法定义描述
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        LogApm metrics = signature.getMethod().getAnnotation(LogApm.class);
        if (metrics == null) {
            metrics = signature.getMethod().getDeclaringClass().getAnnotation(LogApm.class);
        }
        String traceId = StringUtils.traceId();
        String name = String.format("【%s】【%s】", signature.getDeclaringType().toString(), signature.toLongString());
        //因为需要默认对所有@RestController标记的Web控制器实现@Metrics注解的功能，在这种情况下方法上必然是没有@Metrics注解的，我们需要获取一个默认注解。虽然可以手动实例化一个@Metrics注解的实例出来，但为了节省代码行数，我们通过在一个内部类上定义@Metrics注解方式，然后通过反射获取注解的小技巧，来获得一个默认的@Metrics注解的实例
        if (metrics == null) {
            @LogApm
            final class c {
            }
            metrics = c.class.getAnnotation(LogApm.class);
        }
        //尝试从请求上下文（如果有的话）获得请求URL，以方便定位问题
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            if (request != null) {
                name += String.format("【%s】", request.getRequestURL().toString());
                String httpTraceId = request.getHeader("traceId");
                if (StringUtils.isNotBlank(httpTraceId)) {
                    traceId = httpTraceId;
                }
            }
        }
        //实现的是入参的日志输出
        if (metrics.logParameters()) {

            log.info(String.format("traceId:[" + traceId + "],【入参日志】调用 %s 的参数是：【%s】", name, JSONObject.toJSONString(pjp.getArgs())));
        }
        //实现连接点方法的执行，以及成功失败的打点，出现异常的时候还会记录日志
        Object returnValue;
        Instant start = Instant.now();
        try {
            returnValue = pjp.proceed();
            if (metrics.recordSuccessMetrics()) {
                //在生产级代码中，我们应考虑使用类似Micrometer的指标框架，把打点信息记录到时间序列数据库中，实现通过图表来查看方法的调用次数和执行时间，在设计篇我们会重点介绍
                log.info(String.format("traceId:[" + traceId + "],【成功打点】调用 %s 成功，耗时：%d ms", name, Duration.between(start, Instant.now()).toMillis()));
            }

        } catch (Exception ex) {
            if (metrics.recordFailMetrics()) {
                log.info(String.format("traceId:[" + traceId + "],【失败打点】调用 %s 失败，耗时：%d ms", name, Duration.between(start, Instant.now()).toMillis()));
            }

            if (metrics.logException()) {
                log.error(String.format("traceId:[" + traceId + "],【异常日志】调用 %s 出现异常！", name), ex);
            }


            //忽略异常的时候，使用一开始定义的getDefaultValue方法，来获取基本类型的默认值
            if (metrics.ignoreException()) {
                returnValue = getDefaultValue(signature.getReturnType());
            } else {
                throw ex;
            }

        }
        //实现了返回值的日志输出
        if (metrics.logReturn()) {
            log.info(String.format("traceId:[" + traceId + "],【出参日志】调用 %s 的返回是：【%s】", name, returnValue));
        }

        return returnValue;
    }
}