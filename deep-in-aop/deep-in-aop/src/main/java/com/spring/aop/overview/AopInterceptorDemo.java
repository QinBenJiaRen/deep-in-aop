package com.spring.aop.overview;

import org.springframework.scheduling.concurrent.DefaultManagedTaskExecutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jiahaifengjv@gmail.com
 * @date 2022/7/16 4:36 PM
 */
public class AopInterceptorDemo {

    public static void main(String[] args) {
        // 前置模式
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(loader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    long startTime = System.currentTimeMillis();
                    try {
                        EchoService echoService = new DefaultEchoService();
                        return echoService.echo((String) args[0]);
                    } finally {
                        long costTime = System.currentTimeMillis() - startTime;
                        System.out.println("echo 方法执行的实现：" + costTime + "ms.");
                    }
                }
                return null;
            }
        });
        EchoService echoService = (EchoService) proxy;
        echoService.echo("hello, world");
    }
}
