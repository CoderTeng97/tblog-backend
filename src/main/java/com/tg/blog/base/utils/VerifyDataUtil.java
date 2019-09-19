package com.tg.blog.base.utils;


import java.util.regex.Pattern;

/**
 * 校验数据工具 TODO 为完成的工具类
 */
@Deprecated
public class VerifyDataUtil {

    public static boolean email(String email){
        String pattern = "[A-z]+[A-z0-9_-]*\\\\@[A-z0-9]+\\\\.[A-z]+";
        return Pattern.matches(pattern ,email);
    }
}
