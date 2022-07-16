package com.spring.aop.overview;

/**
 * @author jiahaifengjv@gmail.com
 * @date 2022/7/16 4:40 PM
 */
public class DefaultEchoService implements EchoService{

    @Override
    public String echo(String message) throws NullPointerException {
        System.out.println("this is default service");
        return message;
    }
}
