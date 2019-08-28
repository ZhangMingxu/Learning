package com.xufree.learning.java.reflect.proxy;

import com.xufree.learning.java.reflect.proxy.interf.TestInterface;
import com.xufree.learning.java.reflect.proxy.invocationhandler.TestInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态创建@{TestInterface}的实现
 *
 * @author zhangmingxu ON 10:31 上午 2019/8/26
 **/
public class ProxyInAction {
    public static void main(String[] args) {
        InvocationHandler handler = new TestInvocationHandler();
        TestInterface impl = (TestInterface) Proxy.newProxyInstance(ProxyInAction.class.getClassLoader()
                , new Class[]{TestInterface.class}
                , handler);
        impl.testMethod("asd");
    }
}
