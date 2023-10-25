
package com.fusion.center.common.threadpool.builder;

import com.fusion.center.common.design.builder.Builder;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
/**
 * 线程工厂build构造者
 * @author haxod
 * **/
public class ThreadFactoryBuilder implements Builder<ThreadFactory> {

    private static final long serialVersionUID = 1L;
    private ThreadFactory backingThreadFactory;
    private String namePrefix;
    private Boolean daemon;
    private Integer priority;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    public ThreadFactoryBuilder() {
    }

    public ThreadFactoryBuilder threadFactory(ThreadFactory backingThreadFactory) {
        this.backingThreadFactory = backingThreadFactory;
        return this;
    }

    public ThreadFactoryBuilder prefix(String namePrefix) {
        this.namePrefix = namePrefix;
        return this;
    }

    public ThreadFactoryBuilder daemon(boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    public ThreadFactoryBuilder priority(int priority) {
        if (priority < 1) {
            throw new IllegalArgumentException(String.format("Thread priority ({}) must be >= {}", priority, 1));
        } else if (priority > 10) {
            throw new IllegalArgumentException(String.format("Thread priority ({}) must be <= {}", priority, 10));
        } else {
            this.priority = priority;
            return this;
        }
    }

    public void uncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    public static ThreadFactoryBuilder builder() {
        return new ThreadFactoryBuilder();
    }

    public ThreadFactory build() {
        return build(this);
    }

    private static ThreadFactory build(ThreadFactoryBuilder builder) {
        ThreadFactory backingThreadFactory = null != builder.backingThreadFactory ? builder.backingThreadFactory : Executors.defaultThreadFactory();
        String namePrefix = builder.namePrefix;
        Boolean daemon = builder.daemon;
        Integer priority = builder.priority;
        Thread.UncaughtExceptionHandler handler = builder.uncaughtExceptionHandler;
        AtomicLong count = null == namePrefix ? null : new AtomicLong();
        return (r) -> {
            Thread thread = backingThreadFactory.newThread(r);
            if (null != namePrefix) {
                thread.setName(namePrefix + "-" + count.getAndIncrement());
            }

            if (null != daemon) {
                thread.setDaemon(daemon);
            }

            if (null != priority) {
                thread.setPriority(priority);
            }

            if (null != handler) {
                thread.setUncaughtExceptionHandler(handler);
            }

            return thread;
        };
    }
}
