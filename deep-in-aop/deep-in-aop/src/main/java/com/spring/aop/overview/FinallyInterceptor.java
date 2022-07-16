package com.spring.aop.overview;

import java.lang.reflect.Method;

/**
 * @author jiahaifengjv@gmail.com
 * @date 2022/7/16 5:00 PM
 * @since
 */
public interface FinallyInterceptor {

    Object finalize(Object proxy, Method method, Object args[]);
}
