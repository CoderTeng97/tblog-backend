package com.tg.blog.base.controller;

import com.auth0.jwt.JWT;
import com.tg.blog.base.annotation.ControllerExceptionProcessor;
import com.tg.blog.base.enums.ResponseMsgType;
import com.tg.blog.base.exception.ResponseCommonException;
import com.tg.blog.base.utils.TokenUtils;
import com.tg.blog.core.model.User;
import com.tg.blog.core.pojo.vo.UserBaseVO;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


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

    /**
     * 获取用户信息
     * @return
     */
    public UserBaseVO getUserInfo(){
        if ("dev".equals(System.getProperty("user.env"))){
            return new UserBaseVO("6e6a455ad84b4e6c881d8608abdb3170","adminUser","18716664880@163.com","admin");
        }
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        try {
            return (UserBaseVO) TokenUtils.getCliamByName(attrs.getRequest().getHeader("token"),"user");
        } catch (Exception e) {
            throw new RuntimeException("BaseController -> Token获取用户信息获取失败",e);
        }
    }
}


