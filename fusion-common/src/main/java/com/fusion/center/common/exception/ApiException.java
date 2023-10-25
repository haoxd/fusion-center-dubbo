package com.fusion.center.common.exception;

import com.fusion.center.common.model.result.ResultStatus;

/**
 * api 异常
 * @author haoxd
 */
public class ApiException extends BaseException {
    public ApiException(String message) {
        super(message, ResultStatus.API_FAILED);
    }
}
