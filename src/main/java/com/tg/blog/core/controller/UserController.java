package com.tg.blog.core.controller;

import com.tg.blog.base.controller.BaseController;
import com.tg.blog.core.pojo.dto.UserRegistoryDTO;
import com.tg.blog.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Object login(String email,String password){
        return  userService.login(email,password);
    }

    @ApiOperation("用户注册")
    @PostMapping("/registry")
    public Object registry(UserRegistoryDTO userRegistoryDTO){
        return responseOk();
    }


}
