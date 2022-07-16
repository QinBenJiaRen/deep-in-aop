package com.spring.aop.overview;

import java.lang.reflect.Method;

/**
 * @author jiahaifengjv@gmail.com
 * @date 2022/7/16 5:08 PM
 * @since
 */
public interface ExceptionInterceptor {

    /**
     * 异常拦截器
     * @param proxy 代理对象
     * @param throwable 异常信息
     * */
    void interceptor(Object proxy, Method method, Object[] args, Throwable throwable);
}
