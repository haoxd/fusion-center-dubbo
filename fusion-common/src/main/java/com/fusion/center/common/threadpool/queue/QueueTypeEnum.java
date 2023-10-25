
package com.fusion.center.common.threadpool.queue;


public enum QueueTypeEnum {
    ARRAY_BLOCKING_QUEUE(1, "ArrayBlockingQueue"),
    LINKED_BLOCKING_QUEUE(2, "LinkedBlockingQueue"),
    LINKED_BLOCKING_DEQUE(3, "LinkedBlockingDeque"),
    SYNCHRONOUS_QUEUE(4, "SynchronousQueue"),
    LINKED_TRANSFER_QUEUE(5, "LinkedTransferQueue"),
    PRIORITY_BLOCKING_QUEUE(6, "PriorityBlockingQueue"),
    RESIZABLE_LINKED_BLOCKING_QUEUE(7, "ResizableCapacityLinkedBlockingQueue");

    public Integer type;
    public String name;

    QueueTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

}
