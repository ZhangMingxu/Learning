package com.xufree.learning.spring.main;

import com.xufree.learning.spring.beans.ParamConfigBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhangmingxu ON 14:02 2019-05-05
 **/
public class ParamConfigMain {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        ParamConfigBean bean = ctx.getBean(ParamConfigBean.class);
        bean.method(null, null);
        System.out.println();

    }
}
