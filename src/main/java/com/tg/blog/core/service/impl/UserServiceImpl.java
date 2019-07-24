package com.tg.blog.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tg.blog.core.model.User;
import com.tg.blog.core.mapper.UserMapper;
import com.tg.blog.core.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.codec.digest.Md5Crypt;
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
    /** 秘钥 */
    public static final String SECRET = "JKKLJOoasdlfj";
    /** token 过期时间*/
    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 10;


    @Override
    public Map<String, Object> login(String email, String password) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        User user = baseMapper.selectOne(queryWrapper.eq("email",email).eq("password",password));
        String token = JWT.create()
                .withClaim("userInfo",JSON.toJSONString(user))
                .withExpiresAt(expiresDate) // expire time
                .sign(Algorithm.HMAC256(SECRET)); // signature
        Map<String,Object> result = new HashMap<>();
        result.put("token",token);
        result.put("username",user.getUsername());
        result.put("userType",user.getUserType());
        return result;
    }
}
