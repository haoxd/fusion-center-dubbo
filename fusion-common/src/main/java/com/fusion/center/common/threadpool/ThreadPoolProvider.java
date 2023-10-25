package com.fusion.center.common.threadpool;


import com.fusion.center.common.threadpool.builder.ThreadFactoryBuilder;
import com.fusion.center.common.threadpool.builder.ThreadPoolBuilder;
import com.fusion.center.common.threadpool.queue.AsyncServiceBlockingQueue;
import com.fusion.center.common.threadpool.queue.CsutomServiceBlockingQueue;
import com.fusion.center.common.threadpool.reject.Wait60sThreadRejectHandler;
import com.fusion.center.common.utils.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author haoxd
 * @description: 系统线程池配置
 */
@Configuration
@EnableAsync
@Slf4j
public class ThreadPoolProvider {


    private static final int POOL_SIZE = ThreadUtil.getSuitableThreadCount();

    private static final BlockingQueue<Runnable> ASYNC_SERVICE_QUEUE = new AsyncServiceBlockingQueue().generateBlockingQueue();

    private static final BlockingQueue<Runnable> CUSTOM_SERVICE_QUEUE = new CsutomServiceBlockingQueue().generateBlockingQueue();

    private static final RejectedExecutionHandler CUSTOM_REJECT_HANDLER = new Wait60sThreadRejectHandler().generateRejected();


    /**
     * 异步任务线程池，当达到线程池最大处理能力后，后续加入的工作线程将 等待60S
     */
    @Bean(name = "asyncServiceExecutor")
    public ThreadPoolExecutor asyncServiceExecutor() {

        return ThreadPoolBuilder.builder()
                .threadFactory(ThreadFactoryBuilder.builder().prefix("async-Service-Executor").build())
                .corePoolSize(POOL_SIZE)
                .maxPoolNum(POOL_SIZE << 1)
                .workQueue(ASYNC_SERVICE_QUEUE)
                .rejected(CUSTOM_REJECT_HANDLER)
                .keepAliveTime(60, TimeUnit.SECONDS)
                .build();

    }


    /**
     * 异步任务线程池，当达到线程池最大处理能力后，后续加入的工作线程将任务提交到提交任务的线程自己去执行该任务。
     */
    @Bean(name = "customServiceExecutor")
    public ThreadPoolExecutor customServiceExecutor() {

        return ThreadPoolBuilder.builder()
                .threadFactory(ThreadFactoryBuilder.builder().prefix("custom-service-").build())
                .corePoolSize(POOL_SIZE)
                .maxPoolNum(POOL_SIZE << 1)
                .workQueue(CUSTOM_SERVICE_QUEUE)
                .rejected(new ThreadPoolExecutor.CallerRunsPolicy())
                .keepAliveTime(60, TimeUnit.SECONDS)
                .build();

    }

}
