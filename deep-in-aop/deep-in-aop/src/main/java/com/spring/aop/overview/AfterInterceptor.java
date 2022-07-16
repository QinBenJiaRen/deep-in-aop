package com.spring.aop.overview;

import java.lang.reflect.Method;

/**
 * @author jiahaifengjv@gmail.com
 * @date 2022/7/16 4:54 PM
 * @since
 */
public interface AfterInterceptor {

    /**
     * 后置执行
     * @param proxy 代理对象
     * */
    Object after(Object proxy, Method method, Object[] args, Object returnResult);
}
