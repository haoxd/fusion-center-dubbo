package com.fusion.center.common.exception;


import com.fusion.center.common.model.result.ResultStatus;

/**
 * 平台错误基类
 *
 * @author haoxd
 */
public class BaseException extends Exception {

    private final ResultStatus resultType;

    public BaseException(String message, Throwable cause, ResultStatus resultType) {
        super(message, cause);
        this.resultType = resultType;
    }

    public BaseException(String message, ResultStatus resultType) {
        super(message);
        this.resultType = resultType;
    }

    public ResultStatus getResultStatus() {
        return resultType;
    }
}
