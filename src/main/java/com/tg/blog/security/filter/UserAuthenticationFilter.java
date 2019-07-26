package com.tg.blog.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tg.blog.base.exception.ResponseCommonException;
import com.tg.blog.base.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CompletionException;

public class UserAuthenticationFilter extends OncePerRequestFilter{


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        /**用户凭证检查*/
        Optional<String> token =Optional.of(httpServletRequest.getHeader("token"));
        token.orElseThrow(
                ()->{ throw  new ResponseCommonException(HttpStatus.FORBIDDEN,"无效的登录凭证,请登录后重试!");}
        );
        try {
           boolean isExpired =  TokenUtils.isExpired(token.get());
           if (isExpired){
               throw  new ResponseCommonException(HttpStatus.FORBIDDEN,"凭证已过期,请重新登录!");
           }
        }catch (Exception e){
            throw  new ResponseCommonException(HttpStatus.FORBIDDEN,"无效的登录凭证,请登录后重试!");
        }

        /**判断用户是否有访问权限*/
    }
}
