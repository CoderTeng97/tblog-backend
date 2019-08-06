package com.tg.blog.core.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tg.blog.base.controller.BaseController;
import com.tg.blog.base.enums.ResponseMsgType;
import com.tg.blog.base.exception.ResponseCommonException;
import com.tg.blog.base.utils.VerifyDataUtil;
import com.tg.blog.core.model.User;
import com.tg.blog.core.pojo.dto.LoginInfoDTO;
import com.tg.blog.core.pojo.dto.UserRegistoryDTO;
import com.tg.blog.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Object login(String email, String password) throws Throwable{
        if (VerifyDataUtil.email(email)) {
            responseFail(ResponseMsgType.EMAIL_ERROR);
        }

        return userService.login(email, password).orElseThrow(() -> {
            throw new ResponseCommonException("用户名或密码不存在");
        });
    }

    @ApiOperation("用户注册")
    @PostMapping("/registry")
    public Object registry(@RequestBody UserRegistoryDTO userRegistoryDTO) {
        boolean isRegistry = userService.registery(userRegistoryDTO);
        if (false == isRegistry){
            responseFail("亲,注册失败啦,请稍后重试沃~");
        }
        return responseOk();
    }

    @ApiOperation("用户邮箱是否存在")
    @GetMapping("/isExistEmail")
    public Boolean isExistEmail(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        int count = userService.count(queryWrapper.eq("email", email));
        Boolean isExist = count > 0 ? true : false ;
        return isExist;
    }

    @ApiOperation("用户名是否存在")
    @GetMapping("/isExistUserName")
    public Boolean isExistUserName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        int count = userService.count(queryWrapper.eq("username", username));
        Boolean isExist = count > 0 ? true : false ;
        return isExist;
    }
}
