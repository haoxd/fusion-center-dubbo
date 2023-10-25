
package com.fusion.center.common.utils;

/**
 * Thread util
 *
 * @author haoxd
 */
public class ThreadUtil {


    private final static int THREAD_MULTIPLER = 2;

    /**
     * 通过内核数，算出合适的线程数；1.5-2倍cpu内核数
     * 默认线程池核心线程数为服务器(核心数 * 2); 最大线程数为(核心数 * 4)
     * @return thread count
     */
    public static int getSuitableThreadCount() {
        final int coreCount = Runtime.getRuntime().availableProcessors();
        int workerCount = 1;
        while (workerCount < coreCount * THREAD_MULTIPLER) {
            workerCount <<= 1;
        }
        return workerCount;
    }

}
