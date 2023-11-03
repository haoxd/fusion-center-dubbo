

package com.fusion.center.core.aop;

import com.fusion.center.common.annotation.LogApmMarker;
import com.fusion.center.common.utils.LogUtil;
import com.fusion.center.core.reflect.ReflectHandler;
import com.fusion.center.core.support.AbstractInterceptor;

import com.fusion.center.core.tracer.Tracer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.lang.reflect.Method;

/**
 * @author haoxd
 */
public class AroundLogHandler extends AbstractInterceptor {

    /**
     * 环绕处理
     *
     * @param chain 代理对象
     * @return 执行结果
     * @throws Throwable throwable
     */
    public Object around(final AopAroundProxyChain chain) throws Throwable {
        Logger logger = getRealLogger(chain);
        mdcLogApmMarkerParam(chain);
        Tracer.trace(traceId(), spanId(), parentId());
        logger.info(LogUtil.processBeginMarker(ReflectHandler.INSTANCE.getRequestParameter(chain)),
                LogUtil.kTYPE_BEGIN);
        long start = System.currentTimeMillis();
        Object o = null;
        try {
            o = chain.getProceed();
        } finally {
            logger.info(LogUtil.processSuccessDoneMarker(o != null
                            ? ReflectHandler.INSTANCE.getResponseParameter(o) : null,
                    System.currentTimeMillis() - start), LogUtil.kTYPE_DONE);
            MDC.clear();
        }
        return o;
    }

    /**
     * 处理LogMaker注解中的参数
     *
     * @param chain chain
     */
    private void mdcLogApmMarkerParam(AopAroundProxyChain chain) {
        Method method = chain.getMethod();
        if (null != method.getAnnotation(LogApmMarker.class)) {
            if (StringUtils.isNotBlank(method.getAnnotation(LogApmMarker.class).desc())) {
                MDC.put(LogUtil.kLOG_KEY_REQUEST_TYPE, method.getAnnotation(LogApmMarker.class).desc());
            }
            if (StringUtils.isNotBlank(method.getAnnotation(LogApmMarker.class).interfaceName())) {
                MDC.put(LogUtil.kLOG_KEY_INTERFACE_NAME, method.getAnnotation(LogApmMarker.class).interfaceName());
            }
        }
    }

    /**
     * 获取实际业务逻辑实现类的logger对象
     *
     * @param aroundLogProxyChain 切点
     * @return 返回能够真正打印日志位置的包名Logger
     */
    private Logger getRealLogger(final AopAroundProxyChain aroundLogProxyChain) {
        return LoggerFactory.getLogger(aroundLogProxyChain.getClazz().getCanonicalName());
    }
}
