package com.fusion.center.common.threadpool;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author haoxd
 * @description: 系统线程池工具类
 */
@Slf4j
public class ThreadPoolProviderUtil {


    public static final String ASYNC_SERVICE_EXECUTOR = "asyncServiceExecutor";

    public static final String CUSTOM_SERVICE_EXECUTOR = "customServiceExecutor";



    public static Executor getAsyncServiceExecutor() {
        return SpringUtil.getBean(ASYNC_SERVICE_EXECUTOR);
    }

    public static Executor getCustomServiceExecutor() {
        return SpringUtil.getBean(CUSTOM_SERVICE_EXECUTOR);
    }


    public static void printStats(ThreadPoolExecutor threadPool) {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            log.info("=========================");
            log.info("Pool Size: {}", threadPool.getPoolSize());
            log.info("Active Threads: {}", threadPool.getActiveCount());
            log.info("Number of Tasks Completed: {}", threadPool.getCompletedTaskCount());
            log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());

            log.info("=========================");
        }, 1, 6, TimeUnit.MINUTES);
    }
}