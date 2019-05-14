package com.xufree.learning.java.reflect;

import java.lang.reflect.Field;

/**
 * 测试强行写入某个属性
 *
 * @author zhangmingxu ON 11:00 2019-05-13
 **/
public class TestBean {
    private Object object;

    public static void main(String[] args) throws IllegalAccessException {
        TestBean testBean = new TestBean();
        Field[] fields = TestBean.class.getDeclaredFields();
        for (Field field : fields) {
            field.set(testBean, "asd");
        }
        System.out.println(testBean.object);

    }
}
