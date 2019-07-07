package com.tg.blog.base.exception;

/**
 * @Author: TengGang
 * @Date: 2019/7/7 12:56
 * @Description:
 */

import org.springframework.http.HttpStatus;

/**
 * 返回异常
 */
public class ResponseCommonException extends  RuntimeException{

    private HttpStatus httpStatus;
    public ResponseCommonException(String msg){
        super(msg);
        httpStatus = HttpStatus.BAD_REQUEST;
    }

    public ResponseCommonException(HttpStatus httpStatus,String msg){
        super(msg);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}