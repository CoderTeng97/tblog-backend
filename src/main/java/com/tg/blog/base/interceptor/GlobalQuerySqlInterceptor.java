package com.tg.blog.base.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 全局查询SQL通用拦截器
 */
@Slf4j
@Intercepts({
  @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class})
})
public class GlobalQuerySqlInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Method  method = invocation.getMethod();
        Object[] args = invocation.getArgs();
        Object target =  invocation.getTarget();
        Object proceed =  invocation.proceed();
        return null;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
