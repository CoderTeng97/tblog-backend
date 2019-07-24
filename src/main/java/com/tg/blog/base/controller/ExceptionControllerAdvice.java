package com.tg.blog.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.tg.blog.base.exception.ResponseCommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @Author: TengGang
 * @Date: 2019/7/7 13:20
 * @Description:
 */
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    JSONObject info = new JSONObject();

    @ExceptionHandler
    public void processExceptions(HttpServletRequest request, HttpServletResponse response, Exception e){
        if( e instanceof ResponseCommonException){
            info.put("code",((ResponseCommonException) e).getHttpStatus().value());
            info.put("msg",e.getMessage());
        }else{
            log.error("服务器内部异常:" , e);
            info.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            info.put("msg","亲,服务器正在抢修中,请稍后再来...");
        }
        try (PrintWriter writer = response.getWriter()){
            writer.print(info.toJSONString());
            writer.flush();
        } catch (IOException ioe) {
            log.error("服务器内部异常:" ,e);
        }
    }


}
