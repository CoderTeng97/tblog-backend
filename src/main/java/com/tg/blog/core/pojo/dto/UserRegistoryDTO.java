package com.tg.blog.core.pojo.dto;

import lombok.Data;

@Data
/**
 * 用户注册参数DTO
 */
public class UserRegistoryDTO {
    private String username;

    private String password;

    private String email;
}
