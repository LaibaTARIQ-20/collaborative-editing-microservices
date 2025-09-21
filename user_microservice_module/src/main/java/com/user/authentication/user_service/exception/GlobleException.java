package com.user.authentication.user_service.exception;

public class GlobleException extends BaseException {
    public GlobleException(String message) {
        super(message);
    }

    public GlobleException(int code, String message) {
        super( message,code);
    }

    public GlobleException(int code, String message, Throwable cause) {
        super( code,message, cause);
    }
}
