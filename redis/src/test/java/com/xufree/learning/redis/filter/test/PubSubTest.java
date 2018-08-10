package com.xufree.learning.redis.filter.test;

import com.xufree.learning.redis.pubsub.Publisher;
import com.xufree.learning.redis.pubsub.SubThread;
import com.xufree.learning.redis.pubsub.Subscriber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class PubSubTest {
    @Resource
    private JedisPool jedisPool;
    @Resource
    private Publisher publisher;

    @Test
    public void Run(){
        Thread thread = new Thread(new SubThread(jedisPool.getResource(),"topic",new Subscriber()));
        publisher.start();
        thread.start();
    }

}
