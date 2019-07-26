package com.tg.blog.base.utils;


import java.util.regex.Pattern;

public class VerifyDataUtil {

    public static boolean isEmail(String email){
        String pattern = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
        return Pattern.matches(pattern ,email);
    }
}
