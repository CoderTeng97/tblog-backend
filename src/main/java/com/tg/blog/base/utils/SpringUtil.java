package com.tg.blog.base.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**工具类
 * Spring
 */
public class SpringUtil {
    public static HttpServletRequest getHttpServletRequest(){
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return  attrs.getRequest();
    }

    public static HttpServletResponse getHttpServletResponse(){
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return  attrs.getResponse();
    }
}
