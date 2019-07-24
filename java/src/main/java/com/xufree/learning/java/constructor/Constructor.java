package com.xufree.learning.java.constructor;

/**
 * 看看初始化对象和将对象的地址传递给引用
 *
 * @author zhangmingxu ON 16:56 2019-07-19
 **/
public class Constructor {
    private Constructor constructor;

    public Constructor() {
        this.constructor = this;
        System.out.println(constructor);
    }

    public static void main(String[] args) {
        new Constructor();
    }
}
