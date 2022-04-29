package com.hunseong.dmaker.exception;

import com.hunseong.dmaker.domain.dto.ErrorResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.hunseong.dmaker.exception.DeveloperErrorCode.*;

/**
 * Created by Hunseong on 2022/04/29
 */
@RestControllerAdvice
public class DeveloperExceptionHandler {

    @ExceptionHandler(DeveloperException.class)
    public ErrorResponse handleDeveloperException(DeveloperException e) {
        return new ErrorResponse(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class
    })
    public ErrorResponse handleBadRequest(Exception e) {
        return new ErrorResponse(INVALID_REQUEST, INVALID_REQUEST.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e) {
        return new ErrorResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR.getMessage());
    }
}
