package com.tg.blog.base.enums;

/**
 * @Author: TengGang
 * @Date: 2019/7/7 14:05
 * @Description:
 */
public enum ResponseMsgType {
    OK("操作成功！"),
    FAIL("亲,操作失败啦~"),
    EMAIL_ERROR("亲,邮箱格式错误");

    private String msg;

    ResponseMsgType(String msg){
        this.msg = msg;
    }

    public String msg(){
        return this.msg;
    }
}
