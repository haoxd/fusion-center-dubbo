package com.fusion.center.common.threadpool.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * escription: 自定义阻塞队列
 * @Author: haoxd
 * @Version: 1.0
 */
public class CsutomServiceBlockingQueue implements CustomBlockingQueue {


    @Override
    public Integer getType() {
        return QueueTypeEnum.LINKED_BLOCKING_QUEUE.type;
    }

    @Override
    public String getName() {
        return "CUSTOM_SERVICE_QUEUE";
    }

    @Override
    public BlockingQueue generateBlockingQueue() {
        return new LinkedBlockingDeque<>(1024);
    }
}
