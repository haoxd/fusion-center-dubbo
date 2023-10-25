package com.fusion.center.restful.config;

import com.fusion.center.common.exception.ApiException;
import com.fusion.center.common.exception.BusinessException;
import com.fusion.center.common.model.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/***
 * 全局异常处理
 * @author haoxd
 * **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionTranslator {


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleError(MissingServletRequestParameterException e) {
        log.warn("Missing Request Parameter", e);
        String message = String.format("Missing Request Parameter: %s", e.getParameterName());
        return Result.buildFailure(message);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result handleError(MethodArgumentTypeMismatchException e) {
        log.warn("Method Argument Type Mismatch", e);
        String message = String.format("Method Argument Type Mismatch: %s", e.getName());
        return Result.buildFailure(message);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleError(MethodArgumentNotValidException e) {
        //log.warn("Method Argument Not Valid", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return Result.buildFailure(message);

    }

    @ExceptionHandler(BindException.class)
    public Result handleError(BindException e) {
        log.warn("Bind Exception", e);
        FieldError error = e.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return Result.buildFailure(message);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleError(ConstraintViolationException e) {
        log.warn("Constraint Violation", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        String message = String.format("%s:%s", path, violation.getMessage());
        return Result.buildFailure(message);

    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handleError(NoHandlerFoundException e) {
        log.error("404 Not Found", e);
        return Result.buildFailure(e.getMessage());

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleError(HttpMessageNotReadableException e) {
        log.error("Message Not Readable", e);
        return Result.buildFailure(e.getMessage());

    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleError(HttpRequestMethodNotSupportedException e) {
        log.error("Request Method Not Supported", e);
        return Result.buildFailure(e.getMessage());

    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handleError(HttpMediaTypeNotSupportedException e) {
        log.error("Media Type Not Supported", e);
        return Result.buildFailure(e.getMessage());

    }

    @ExceptionHandler(BusinessException.class)
    public Result handleError(BusinessException e) {
        log.error("BusinessException ", e);
        return Result.buildFailure(e.getMessage());

    }

    @ExceptionHandler(ApiException.class)
    public Result handleError(ApiException e) {
        log.error("ApiException ", e);
        return Result.buildFailure(e.getMessage());

    }

    @ExceptionHandler(NullPointerException.class)
    public Result handleError(NullPointerException e) {
        log.error("NullPointer Exception", e);
        return Result.buildFailure(e.getMessage());

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleError(IllegalArgumentException e) {
        log.error("IllegalArgumentException Exception", e);
        return Result.buildFailure(e.getMessage());


    }


    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result handleError(Exception e) {
        log.error("Exception ", e);
        return Result.buildFailure(e.getMessage());


    }

    @ExceptionHandler(Throwable.class)
    public Result handleError(Throwable e) {
        log.error("Internal Server Error", e);
        return Result.buildFailure(e.getMessage());

    }
}
