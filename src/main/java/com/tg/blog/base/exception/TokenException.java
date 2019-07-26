package com.tg.blog.base.exception;

public class TokenException extends RuntimeException{

    public TokenException() {
    }

    public TokenException(String message) {
        super(message);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenException TokenNotFoundException(){
        return new TokenException("Token未找到");
    }
}
