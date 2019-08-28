package com.xufree.learning.java.reflect.proxy.invocationhandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author zhangmingxu ON 10:40 上午 2019/8/26
 **/
public class TestInvocationHandler implements InvocationHandler {
    private static final String test = "test";

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("实现方法:" + method.getName());
        System.out.println("参数类型:"+ Arrays.toString(method.getParameterTypes()));
        System.out.println("参数:" + Arrays.toString(args));
        System.out.println("getReturnType:"+method.getReturnType());
        System.out.println("getAnnotatedReturnType:"+method.getAnnotatedReturnType());
        System.out.println("getGenericReturnType:"+method.getGenericReturnType());
        return null;
    }
}
