package com.tg.blog.core.pojo.vo;

import lombok.Data;

/**
 * @Author: TengGang
 * @Date: 2019/7/20 18:54
 * @Description:
 */
@Data
public class UserBaseVO {
    private String id;

    private String username;

    private String email;

    private String userType;

    public UserBaseVO() {
    }

    public UserBaseVO(String id, String username, String email, String userType) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.userType = userType;
    }


}
