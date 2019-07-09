package com.tg.blog.base.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tg.blog.base.annotation.ControllerExceptionProcessor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

/**
 * @Author: TengGang
 * @Date: 2019/7/7 13:48
 * @Description: 对controller层返回成功的结果进行包装
 */
@ControllerAdvice(basePackages = {"com.tg.blog.core.controller"})
public class GlobalResponseAdvice implements ResponseBodyAdvice{
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        Class clazz = returnType.getContainingClass();
        Class superClazz = clazz.getSuperclass();
        ControllerExceptionProcessor exceptionProcessor = (ControllerExceptionProcessor) superClazz.getAnnotation(ControllerExceptionProcessor.class);
        if(exceptionProcessor != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        JSONObject info = new JSONObject();
        info.put("code", HttpStatus.OK.value());
        info.put("msg",HttpStatus.OK.getReasonPhrase());
        if (!ObjectUtils.isEmpty(body)){
            if(body instanceof  String){
                info.put("data", body);
                return info.toJSONString();
            }else{
                info.put("data",body);
            }

        }
        return info;
    }
}
