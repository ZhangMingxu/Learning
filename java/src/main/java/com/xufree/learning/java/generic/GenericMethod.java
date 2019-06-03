package com.xufree.learning.java.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 范型方法
 *
 * @author zhangmingxu ON 14:42 2019-06-03
 **/
public class GenericMethod {
    /**
     * 通过方法入参决定方法返回值类型
     * <T>必须放在访问控制符之后返回值之前
     *
     * @param type 返回值类型class
     * @param <T>  范型
     * @return 指定类型的对象
     */
    private <T> T returnT(Class<T> type) {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 测试带有super关键字的范型
     * 只有执行Object方法的能力
     * ? super X
     * 可以是X或者X的父类
     */
    private void genericWithSuper(Class<? super List> type) {
        try {
            //只能使用Object类的方法
            type.newInstance().hashCode();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试带有extends关键字的范型
     * 可以执行对应类的方法
     * ? extends X
     * 可以是X或是X的子类
     */
    private void genericWithExtends(Class<? extends List> type) {
        try {
            //可以执行List的方法
            type.newInstance().size();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GenericMethod test = new GenericMethod();
        //测试返回值
        String string = test.returnT(String.class);
        System.out.println(string);

        //测试supper ? super List
        //List.class可以(自己)
        test.genericWithSuper(List.class);
        //ArrayList.class不行(子类)
        //test.genericWithSuper(ArrayList.class);
        //Object.class可以(父类)
        test.genericWithSuper(Object.class);

        //测试supper ? extend List
        //List.class可以(自己)
        test.genericWithExtends(List.class);
        //ArrayList.class可以(子类)
        test.genericWithExtends(ArrayList.class);
        //Object.class不行(父类)
        //test.genericWithExtends(Object.class);
    }
}
