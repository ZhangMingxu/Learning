package com.xufree.learning.redis.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Publisher {
    @Resource
    private JedisPool jedisPool;
    private Jedis jedis;
    private String topic;

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @PostConstruct
    public void init() {
        this.jedis = jedisPool.getResource();
    }

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line;
            try {
                line = reader.readLine();
                if (!"quit".equals(line)) {
                    jedis.publish(topic, line);
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
