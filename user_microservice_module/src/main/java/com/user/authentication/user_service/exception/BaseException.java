package com.user.authentication.user_service.exception;

public class BaseException extends  RuntimeException {
    private  int code;

    public BaseException(int code) {
        this.code = code;
    }
    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, int code) {
        super(message);
        this.code = code;
    }

    public BaseException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public final int getCode() {
        return this.code;
    }

    public String getMessage() {
        return super.getMessage();
    }
}
