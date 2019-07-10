package com.tg.blog.core.controller;

import com.alibaba.fastjson.JSONObject;
import com.tg.blog.base.controller.BaseController;
import com.tg.blog.core.service.AliYunOSService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/oss")
public class AliYunOSSController extends BaseController{

    @Autowired
    AliYunOSService aliYunOSService;

    @GetMapping("/wpolicy")
    @ApiOperation("获取读写签名")
    public Object getWritePolicy() throws UnsupportedEncodingException {
        /**生成用户dir*/
        JSONObject result =  aliYunOSService.getWritePolicy("userArticleCover");
        return  result;
    }
}
