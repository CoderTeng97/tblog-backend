package com.tg.blog.core.controller;

import com.tg.blog.base.controller.BaseController;
import com.tg.blog.core.pojo.dto.UserRegistoryDTO;
import com.tg.blog.core.pojo.vo.UserBaseVO;
import com.tg.blog.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Api(description = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Object login(String email, String password, HttpServletRequest request){
        Optional<UserBaseVO> userBaseVO = userService.login(email,password);
        if (!userBaseVO.isPresent()){
            responseFail("用户账号或密码错误");
        }
        HttpSession session =  request.getSession();
        session.setAttribute("user"+userBaseVO.get().getId(),userBaseVO.get());
        return responseOk();
    }

    @ApiOperation("用户注册")
    @PostMapping("/registry")
    public Object registry(UserRegistoryDTO userRegistoryDTO){
        return responseOk();
    }


}
