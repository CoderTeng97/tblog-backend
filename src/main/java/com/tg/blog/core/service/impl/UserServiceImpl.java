package com.tg.blog.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tg.blog.base.exception.ResponseCommonException;
import com.tg.blog.base.exception.TokenException;
import com.tg.blog.base.utils.TokenUtils;
import com.tg.blog.core.model.User;
import com.tg.blog.core.mapper.UserMapper;
import com.tg.blog.core.pojo.dto.LoginInfoDTO;
import com.tg.blog.core.pojo.vo.UserBaseVO;
import com.tg.blog.core.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javassist.NotFoundException;
import net.sf.jsqlparser.parser.TokenMgrException;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Teng
 * @since 2019-07-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



    @Override
    public Optional<Map<String,Object>> login(String email , String password){
        UserBaseVO user = baseMapper.selectUserByLoginInfo(email,password);
        if (user == null){return null;}
        Optional token = TokenUtils.generateUserToken(user);
        Map<String,Object> map = new HashMap<>();
        map.put("token",token.get());
        map.put("user",user);
        return Optional.of(map);
    }
}
