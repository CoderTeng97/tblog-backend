package com.tg.blog.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.tg.blog.base.exception.ResponseCommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class ExceptionHandlerFilter implements Filter{



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
        }catch (Exception e){
            JSONObject info = new JSONObject();
            if( e instanceof ResponseCommonException){
                info.put("code",((ResponseCommonException) e).getHttpStatus().value());
                info.put("msg",e.getMessage());
            }else{
                log.error("服务器内部异常:" , e);
                info.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
                info.put("msg","亲,服务器正在抢修中,请稍后再来...");
            }
            try (PrintWriter writer = servletResponse.getWriter()){
                writer.print(info.toJSONString());
                writer.flush();
            } catch (IOException ioe) {
                log.error("服务器内部异常:" ,e);
            }
        }
    }

}
