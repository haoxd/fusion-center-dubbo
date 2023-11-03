

package com.fusion.center.core.tracer;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.fusion.center.common.utils.StringUtils;
import lombok.Data;
import org.slf4j.MDC;

/**
 * @author haoxd
 */
@Data
public final class Tracer {

    public static final String TRACE_ID = "traceId";

    public static final String SPAN_ID = "spanId";

    public static final String PARENT_ID = "parentId";

    public static final String SKYWALKING_NO_ID = "[Ignored Trace]";

    private static TransmittableThreadLocal<Tracer> traceThreadLocal = new TransmittableThreadLocal<>();

    private String traceId;

    private Integer spanId;

    private Integer parentId;

    public Tracer(TracerBuilder builder) {
        this.traceId = builder.traceId;
        this.spanId = builder.spanId;
        this.parentId = builder.parentId;
        traceThreadLocal.set(this);
    }

    /**
     * trace root
     *
     * @param traceId  the trace id
     * @param spanId   the span id
     * @param parentId the parent id
     */
    public static void trace(String traceId, String spanId, String parentId) {
        Tracer tracer = Tracer.traceThreadLocal.get();
        if (null == tracer) {
            tracer = new TracerBuilder()
                    .traceId(traceId)
                    .spanId(null == spanId ? null : Integer.parseInt(spanId))
                    .parentId(null == parentId ? null : Integer.parseInt(parentId))
                    .build();
            tracer.buildSpan();
        } else {
            traceThreadLocal.remove();
        }
    }

    /**
     * build span
     */

    public void buildSpan() {
        if (null == traceId) {
            traceId = StringUtils.traceId();
        }
        parentId = null == parentId ? 0 : parentId + 1;
        spanId = null == spanId ? 1 : spanId + 1;
        MDC.put(TRACE_ID, traceId);
        MDC.put(SPAN_ID, spanId + "");
        MDC.put(PARENT_ID, parentId + "");
    }

    /**
     * trace builder
     */
    public static class TracerBuilder {

        private String traceId;

        private Integer spanId;

        private Integer parentId;

        /**
         * build trace id
         *
         * @param traceId trace id
         * @return trace id
         */
        public TracerBuilder traceId(String traceId) {
            this.traceId = traceId;
            return this;
        }

        /**
         * build span id
         *
         * @param spanId span id
         * @return span id
         */
        public TracerBuilder spanId(Integer spanId) {
            this.spanId = spanId;
            return this;
        }

        /**
         * build parent id
         *
         * @param parentId parent id
         * @return parent id
         */
        public TracerBuilder parentId(Integer parentId) {
            this.parentId = parentId;
            return this;
        }

        /**
         * build
         *
         * @return Tracer
         */
        public Tracer build() {
            return new Tracer(this);
        }
    }
}
