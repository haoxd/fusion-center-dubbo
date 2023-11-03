

package com.fusion.center.core.reflect;

import com.fusion.center.common.utils.LogUtil;
import com.fusion.center.core.aop.AopAroundProxyChain;
import com.fusion.center.spi.ExtensionLoader;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

/**
 * @author haoxd
 */
@Slf4j
public final class ReflectHandler {

    public static final ReflectHandler INSTANCE = new ReflectHandler();

    private final Set<String> spiName = Sets.newHashSet();


    private ReflectHandler() {
        spiName.add("java.util.Map");
        spiName.add("java.util.List");
        spiName.add("java.util.Set");
        spiName.add("javax.servlet.http.HttpServletRequest");
    }

    /**
     * 请求参数解析
     *
     * @param chain chain
     * @return 请求参数
     */
    public Object getRequestParameter(final AopAroundProxyChain chain) {
        List<Object> result = Lists.newArrayList();
        chain.parameter().forEach((k, v) -> v.forEach(o -> {
            try {
                result.add(ExtensionLoader
                        .getExtensionLoader(Reflect.class)
                        .getJoin(spiName.contains(k) ? k : "default").reflect(o));
            } catch (UnsupportedEncodingException e) {
                log.error(LogUtil.exceptionMarker(), "UnsupportedEncodingException", e);
            }
        }));
        return result;
    }

    /**
     * 响应参数解析
     *
     * @param o object
     * @return 响应参数
     */
    public Object getResponseParameter(final Object o) {
        Object result = null;
        try {
            result = ExtensionLoader.getExtensionLoader(Reflect.class)
                    .getJoin(spiName.contains(o.getClass().getName()) ? o.getClass().getName() : "default")
                    .reflect(o);
        } catch (UnsupportedEncodingException e) {
            log.error(LogUtil.exceptionMarker(), "UnsupportedEncodingException", e);
        }
        return result;
    }




}
