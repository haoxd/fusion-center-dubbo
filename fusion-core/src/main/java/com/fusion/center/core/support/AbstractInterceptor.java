

package com.fusion.center.core.support;


import com.fusion.center.common.utils.LogUtil;
import com.fusion.center.core.tracer.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import static com.fusion.center.common.constant.TraceTagConstant.START_TIME;
import static net.logstash.logback.marker.Markers.append;


/**
 * @author haoxd
 */
@Slf4j
public abstract class AbstractInterceptor {

    /**
     * get trace id.
     *
     * @return trace id
     */
    public String traceId() {
        return MDC.get(Tracer.TRACE_ID);
    }

    /**
     * get span id.
     *
     * @return span id
     */
    public String spanId() {
        return MDC.get(Tracer.SPAN_ID);
    }

    /**
     * get parent id.
     *
     * @return parent id
     */
    public String parentId() {
        return MDC.get(Tracer.PARENT_ID);
    }

    /**
     * before
     *
     * @param traceTag 标签
     */
    protected void executeBefore(final String traceTag) {
        MDC.put(START_TIME, String.valueOf(System.currentTimeMillis()));
        log.info(LogUtil.customizeMarker(LogUtil.kLOG_KEY_TRACE_TAG, traceTag), traceTag);
    }

    /**
     * after
     *
     * @param traceTag 标签
     */
    protected void executeAfter(final String traceTag) {
        String start = MDC.get(START_TIME);
        if (StringUtils.isNotBlank(start)) {
            long startTime = Long.parseLong(start);
            long duration = System.currentTimeMillis() - startTime;
            log.info(LogUtil.customizeMarker(LogUtil.kLOG_KEY_TRACE_TAG, traceTag)
                            .and(append(LogUtil.kLOG_KEY_DURATION, duration)), traceTag);
        }
    }
}
