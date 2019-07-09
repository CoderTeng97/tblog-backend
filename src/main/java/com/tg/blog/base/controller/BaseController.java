package com.tg.blog.base.controller;

import com.tg.blog.base.annotation.ControllerExceptionProcessor;
import com.tg.blog.base.enums.ResponseMsgType;
import com.tg.blog.base.exception.ResponseCommonException;
import org.springframework.http.HttpStatus;


/**
 * @Author: TengGang
 * @Date: 2019/7/6 16:35
 * @Description:
 */
@ControllerExceptionProcessor
public abstract class BaseController {


    /**
     * 返回成功信息
     * @param
     * @return
     */
    public Object responseOk(){
        return  ResponseMsgType.OK.msg();
    }
    public Object responseOk(String msg){
        return  msg;
    }
    public Object responseOk(Object data){
        return  data;
    }
    public Object responseOk(ResponseMsgType responseMsgType){
        return  responseMsgType.msg();
    }



    /**
     * 返回失败信息
     * @param
     * @return
     */
    public void responseFail(String msg){
        throw  new ResponseCommonException(msg);
    }

    public void responseFail(ResponseMsgType responseMsgType){
        throw  new ResponseCommonException(responseMsgType.msg());
    }
}


