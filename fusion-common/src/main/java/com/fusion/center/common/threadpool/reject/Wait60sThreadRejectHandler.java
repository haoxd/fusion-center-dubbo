package com.fusion.center.common.threadpool.reject;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义实现的拒绝策略
 *
 * @author haoxd
 * */
@Slf4j
public class Wait60sThreadRejectHandler implements CustomRejectedExecutionHandler {

    @Override
    public Integer getType() {
        return 12;
    }

    @Override
    public RejectedExecutionHandler generateRejected() {
        return new ThreadRejectHandler();
    }


    /**
     * 自定义实现的拒绝策略
     **/
    private static class ThreadRejectHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                TimeUnit.SECONDS.sleep(60);
            } catch (InterruptedException e) {
                log.error("线程休眠被中断");
            }

            executor.execute(r);
        }
    }
}