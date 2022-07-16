package com.spring.aop.overview;

import java.lang.reflect.Method;

/**
 * @author jiahaifengjv@gmail.com
 * @date 2022/7/16 4:46 PM
 */
public interface BeforeInterceptor {

    /**
     * 前置执行
     * @param proxy 代理对象
     *
     * */
    Object before(Object proxy, Method method, Object[] args);
}
