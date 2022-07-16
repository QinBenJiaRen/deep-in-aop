package com.spring.aop.overview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jiahaifengjv@gmail.com
 * @date 2022/7/16 4:50 PM
 * @since
 */
public class BeforeAopInterceptorDemo {

    public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(loader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    // 前置拦截器
                    BeforeInterceptor interceptor = (proxy1, method1, args1) -> System.currentTimeMillis();
                    Long startTime = 0L;
                    Long endTime = 0L;
                    try {
                        startTime = (Long) interceptor.before(proxy, method, args);
                        EchoService echoService = new DefaultEchoService();
                        Object result = echoService.echo((String) args[0]);
                        // 后置拦截
                        AfterInterceptor afterInterceptor = (proxy12, method12, args12, returnResult) -> System.currentTimeMillis();
                        endTime = (Long) afterInterceptor.after(proxy, method, args, result);
                    } catch (Exception e) {
                        // 异常拦截器
                        ExceptionInterceptor exceptionInterceptor = (proxy13, method13, args13, throwable) -> System.out.println("this is exception interceptor");
                    } finally {
                        FinallyInterceptor finallyInterceptor = new TimeFinallyInterceptor(startTime, endTime);
                        Long costTime = (Long) finallyInterceptor.finalize(proxy, method, args);
                    }


                    // finally 后置拦截器

                }
                return null;
            }
        });
        EchoService echoService = (EchoService) proxy;
        echoService.echo("hello world");
    }


}

class TimeFinallyInterceptor implements FinallyInterceptor{

    private final Long startTime;

    private final Long endTime;

    TimeFinallyInterceptor(Long startTime, Long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }


    @Override
    public Object finalize(Object proxy, Method method, Object[] args) {
        return endTime - startTime;
    }
}
