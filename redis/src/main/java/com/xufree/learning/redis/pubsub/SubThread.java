package com.xufree.learning.redis.pubsub;

import redis.clients.jedis.Jedis;

public class SubThread implements Runnable {
    private Jedis jedis;
    private String channel;
    private Subscriber subscriber;

    public SubThread(Jedis jedis, String channel, Subscriber subscriber) {
        this.jedis = jedis;
        this.channel = channel;
        this.subscriber = subscriber;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            jedis.subscribe(subscriber, channel);
        } catch (Exception e) {
            System.out.println(String.format("subsrcibe channel error, %s", e));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
