package com.tg.blog.security.filter;


import com.tg.blog.base.FilterResponseUtil;
import com.tg.blog.base.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
public class UserAuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        /**用户凭证检查*/
        Optional<String> token = Optional.ofNullable(httpServletRequest.getHeader("token"));
        try {
            if (!token.isPresent()) {
                FilterResponseUtil.response("无效的登录凭证", HttpStatus.FORBIDDEN);
                return;
            }

        } catch (Throwable throwable) {
            FilterResponseUtil.response("亲，系统内部错误，请稍后重试~", HttpStatus.INTERNAL_SERVER_ERROR);
            logger.error("doFilterInternal 用户凭证检查错误 ->", throwable);
            return;
        }

        try {
            boolean isExpired = TokenUtils.isExpired(token.get());
            if (isExpired) {
                FilterResponseUtil.response("凭证已过期,请重新登录!", HttpStatus.FORBIDDEN);
                return;
            }
        } catch (Exception e) {
            FilterResponseUtil.response("无效的登录凭证,请登录后重试!", HttpStatus.FORBIDDEN);
            return;
        }
        doFilter(httpServletRequest, httpServletResponse, filterChain);
        /**判断用户是否有访问权限*/
    }
}
