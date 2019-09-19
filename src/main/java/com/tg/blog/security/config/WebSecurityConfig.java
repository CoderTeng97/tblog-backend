package com.tg.blog.security.config;


import com.tg.blog.security.filter.CustomCorsFilter;
import com.tg.blog.security.filter.UserAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 设置 HTTP 验证规则
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // 基于token，不需要csrf，关闭csrf验证
        httpSecurity.csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable() //不要UsernamePasswordAuthenticationFilter
                .httpBasic().disable(); //不要BasicAuthenticationFilter
                //.authorizeRequests().anyRequest().access("hasIpAddress('127.0.0.1') or authenticated");

        // 禁用缓存
        httpSecurity.headers().cacheControl();
        //校验Token
        httpSecurity.addFilterBefore(new UserAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
        // 添加CORS过滤器
        httpSecurity.addFilterAfter(new CustomCorsFilter(), UserAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // AuthenticationTokenFilter will ignore the below paths
        // 放行swagger 和静态资源

        web.ignoring().antMatchers("/swagger-ui.html",
                "/v2/api-docs",
                "/configuration/**",
                "/swagger*/**",
                "/webjars/**",
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js");
        //放行认证到controller TODO 后期可扩展到配置系统 String[]（或者写一个open controller） 每个开发接口做访问权限控制，设置频繁访问限制
        web.ignoring().antMatchers(HttpMethod.POST,
                "/user/login",
                "/user/registry"
                );

        web.ignoring().antMatchers(HttpMethod.GET,
                "/user/isExistUserName",
                "/user/isExistEmail"
        );


    }
}