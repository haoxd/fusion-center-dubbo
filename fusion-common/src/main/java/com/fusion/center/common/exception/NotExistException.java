package com.fusion.center.common.exception;

import com.fusion.center.common.model.result.ResultStatus;

/**
 * 不存在异常
 * @author haoxd
 */
public class NotExistException extends BaseException {
    public NotExistException(String message) {
        super(message, ResultStatus.NOT_EXIST);
    }
}
