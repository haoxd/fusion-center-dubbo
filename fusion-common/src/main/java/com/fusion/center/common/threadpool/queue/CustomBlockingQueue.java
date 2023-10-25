
package com.fusion.center.common.threadpool.queue;

import java.util.concurrent.BlockingQueue;

public interface CustomBlockingQueue {

    Integer getType();

    default String getName() {
        return "";
    }

    BlockingQueue generateBlockingQueue();
}
