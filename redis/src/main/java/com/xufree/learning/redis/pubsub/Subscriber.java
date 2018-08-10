package com.xufree.learning.redis.pubsub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

public class Subscriber extends JedisPubSub{
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void onMessage(String channel, String message) {
        logger.info("接收到[channel={}]的信息[message={}]",channel,message);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        super.onSubscribe(channel, subscribedChannels);
    }
}
