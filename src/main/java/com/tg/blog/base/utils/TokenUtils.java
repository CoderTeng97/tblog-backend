package com.tg.blog.base.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tg.blog.base.exception.TokenException;
import org.springframework.util.StringUtils;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

/***
 * Token utils
 * This is used to generate kind of token and decode it.
 */
public class TokenUtils {
    private final static String secret;
    private final static long expires;

    static {
        secret = Optional.ofNullable(System.getProperty("security.token.secret")).orElse(UUID.randomUUID().toString());
        expires = Long.valueOf(Optional.ofNullable(System.getProperty("security.token.expires")).orElse("1"));
    }

    /**
     * 生成用户token
     *
     * @param user
     * @return
     * @throws Exception
     */
    public static Optional<String> generateUserToken(Object user) {
        try {
            return Optional.ofNullable(
                    JWT.create()
                            .withClaim("user", JSON.toJSONString(user))
                            .withExpiresAt(DateUtils.transformToDate(LocalDateTime.now().minusHours(expires)))
                            .sign(Algorithm.HMAC256(secret))
            );
        } catch (Exception e) {
            throw new TokenException("Token 生成失败", e);
        }
    }

    /**
     * 获取自定义token信息通过名称
     *
     * @param token
     * @param name
     * @return
     * @throws Exception
     */
    public static Object getCliamByName(String token, String name,Class retrunClazz) throws Exception {
        try {
            String jsonStr = JWT.decode(token)
                    .getClaim(name).asString();
            Object obj = JSONObject.parseObject(jsonStr,retrunClazz);
            return obj;
        } catch (Exception e) {
            throw new TokenException("Token 获取失败", e);
        }
    }

    /**
     * 是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpired(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secret));
            Date date = JWT.decode(token).getExpiresAt();
            return new Date().before(date);
        } catch (Exception e) {
            throw new TokenException("Token 是否过期判断错误", e);
        }

    }
}
