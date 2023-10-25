

package com.fusion.center.common.threadpool.builder;


import com.fusion.center.common.design.builder.Builder;

import java.util.concurrent.*;

/**
 * 线程池构造者
 *
 * @author haoxd
 **/
public class ThreadPoolBuilder implements Builder<ThreadPoolExecutor> {

    private int corePoolSize;
    private int maxPoolSize;
    private long keepAliveTime;
    private TimeUnit timeUnit;

    private BlockingQueue workQueue;
    private RejectedExecutionHandler rejectedExecutionHandler;

    private ThreadFactory threadFactory;


    public ThreadPoolBuilder corePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    public ThreadPoolBuilder maxPoolNum(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
        return this;
    }

    public ThreadPoolBuilder keepAliveTime(long keepAliveTime, TimeUnit timeUnit) {
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        return this;
    }


    public ThreadPoolBuilder threadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        return this;
    }


    public ThreadPoolBuilder rejected(RejectedExecutionHandler rejectedExecutionHandler) {
        this.rejectedExecutionHandler = rejectedExecutionHandler;
        return this;
    }


    public ThreadPoolBuilder workQueue(BlockingQueue workQueue) {
        this.workQueue = workQueue;
        return this;
    }


    public ThreadPoolExecutor build() {
        return new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, workQueue, threadFactory, rejectedExecutionHandler);
    }

    public static ThreadPoolBuilder builder() {
        return new ThreadPoolBuilder();
    }

}
