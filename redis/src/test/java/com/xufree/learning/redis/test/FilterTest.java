package com.xufree.learning.redis.test;

import com.xufree.learning.redis.BloomFilter;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FilterTest {
    @Test
    public void testFilter() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        BloomFilter filter = context.getBean("bloomFilter", BloomFilter.class);
        System.out.println(filter.isExist("aaa", "aaa"));
        System.out.println(filter.isExist("aaa", "bbb"));
    }
}
