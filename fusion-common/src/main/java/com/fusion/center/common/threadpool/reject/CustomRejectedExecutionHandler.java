
package com.fusion.center.common.threadpool.reject;

import java.util.concurrent.RejectedExecutionHandler;

public interface CustomRejectedExecutionHandler {
    Integer getType();

    default String getName() {
        return "";
    }

    RejectedExecutionHandler generateRejected();
}
