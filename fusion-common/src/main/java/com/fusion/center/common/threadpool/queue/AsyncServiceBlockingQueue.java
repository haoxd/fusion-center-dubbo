package com.fusion.center.common.threadpool.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Description: 异步服务阻塞队列
 * @Author: haoxd
 * @Version: 1.0
 */
public class AsyncServiceBlockingQueue implements CustomBlockingQueue {


    @Override
    public Integer getType() {
        return QueueTypeEnum.LINKED_BLOCKING_DEQUE.type;
    }

    @Override
    public BlockingQueue generateBlockingQueue() {
       return new LinkedBlockingDeque<>(1024);

    }

    @Override
    public String getName() {
        return "ASYNC_SERVICE_QUEUE";
    }
}
