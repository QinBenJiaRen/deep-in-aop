package com.spring.aop.overview;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author jiahaifengjv@gmail.com
 * @date 2022/7/16 10:39 AM
 */
public class TargetFilterDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        String targetClassName = "com.spring.aop.overview.EchoService";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<?> targetClass = loader.loadClass(targetClassName);
        // Spring 反射工具类
        Method method = ReflectionUtils.findMethod(targetClass, "echo", String.class);
        System.out.println(method);
        // 查找方法 throws 类型为 NullPointerException
        ReflectionUtils.doWithMethods(targetClass, new ReflectionUtils.MethodCallback() {
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                System.out.println("仅抛出 NullPointerException 方法为:" + method);
            }
        }, new ReflectionUtils.MethodFilter() {
            @Override
            public boolean matches(Method method) {
                Class[] parameterTypes = method.getParameterTypes();
                Class[] exceptionTypes = method.getExceptionTypes();
                return  parameterTypes.length == 1
                        && String.class.equals(parameterTypes[0])
                        && exceptionTypes.length == 1
                        && NullPointerException.class.equals(exceptionTypes[0]);
            }
        });

    }
}
