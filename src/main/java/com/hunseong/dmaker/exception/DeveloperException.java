package com.hunseong.dmaker.exception;

/**
 * Created by Hunseong on 2022/04/29
 */
public class DeveloperException extends RuntimeException {

    private DeveloperErrorCode code;
    private String message;

    public DeveloperException(DeveloperErrorCode code) {
        super(code.getMessage());
        this.code = code;
        this.message = code.getMessage();
    }

    public DeveloperException(DeveloperErrorCode code, String message) {
        super(code.getMessage());
        this.code = code;
        this.message = message;
    }
}
