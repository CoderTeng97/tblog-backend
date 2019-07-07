package com.tg.blog.base.annotation;

import java.lang.annotation.*;

/**
 * @Author: TengGang
 * @Date: 2019/7/7 13:41
 * @Description:
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerExceptionProcessor {
}
