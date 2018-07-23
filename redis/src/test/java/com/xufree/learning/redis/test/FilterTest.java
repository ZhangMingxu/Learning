package com.xufree.learning.redis.test;

import com.xufree.learning.redis.BloomFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class FilterTest {
    @Resource
    BloomFilter bloomFilter;

    @Test
    public void testFilter() {
        System.out.println(bloomFilter.isExist("aaa", "aaa"));
        System.out.println(bloomFilter.isExist("aaa", "bbb"));
    }
}
