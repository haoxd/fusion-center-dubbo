package com.fusion.center.common.exception;

import com.fusion.center.common.model.result.ResultStatus;

/**
 * 业务操作 异常
 *
 * @author haoxd
 */
public class BusinessException extends BaseException {
    public BusinessException(String message) {
        super(message, ResultStatus.BUSINESS_FAILED);
    }
}
