package com.tg.blog.base;

import com.alibaba.fastjson.JSONObject;
import com.tg.blog.base.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 处理filter中 错误返回数据
 */
@Slf4j
public class FilterResponseUtil {
    public static void response(String msg, HttpStatus httpStatus) {
        HttpServletResponse response = SpringUtil.getHttpServletResponse();
        JSONObject info =  new JSONObject();
        info.put("code", httpStatus.value());
        info.put("msg", httpStatus.getReasonPhrase());
        info.put("data", msg);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        try( PrintWriter printWriter = response.getWriter()) {
            printWriter.write(info.toJSONString());
        } catch (IOException e) {
            log.error("FilterResponseUtil -> filter异常处理返回数据报错",e);
        }
    }
}
